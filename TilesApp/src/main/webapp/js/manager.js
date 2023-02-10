/**
 *
 */
$(document).ready(function () {
  // 목록 가져오는 Ajax 호출
  $.ajax({
    url: "memberList.do",
    success: function (result) {
      /*  result.forEach((item) => {
				makeRow(item);
			}); */
      $(result).each(function (idx, item) {
        $("#list").append(makeRow(item));
      });
    },
    error: function (reject) {
      console.log(reject);
    },
  });

  // 등록 이벤트
  $("#addBtn").on("click", function () {
    let id = $("#mid").val();
    let name = $("#mname").val();
    let phone = $("#mphone").val();
    let addr = $("#maddr").val();
    let image = $("#mimage")[0].files[0];

    let formData = new FormData();
    formData.append("member_id", id);
    formData.append("member_name", name);
    formData.append("member_phone", phone);
    formData.append("member_addr", addr);
    formData.append("image", image);
    formData.append("from", "manager");

    $.ajax({
      url: "signOn.do",
      method: "post",
      data: formData,
      contentType: false,
      processData: false,
      success: function (result) {
        // 처리된 정보를 화면에 생성
        if (result.retCode == "Success") {
          $("#list").append(makeRow(result.member));
        } else {
          alert("입력 중 오류 발생!");
        }
      },
      error: function (reject) {
        console.log(reject);
      },
    });

    $("#mid").val("");
    $("#mname").val("");
    $("#mphone").val("");
    $("#maddr").val("");
    //$("mimage");
  });

  function makeRow(member = {}) {
    let tr = $("<tr />");
    tr.on("dblclick", function (ev) {
      let id = $(ev.target).closest("tr").children().eq(0).text();
      let name = $(ev.target).closest("tr").children().eq(1).text();
      let phone = $(ev.target).closest("tr").children().eq(2).text();
      let addr = $(ev.target).closest("tr").children().eq(3).text();
      let resp = $(ev.target).closest("tr").children().eq(4).text();

      let nTr = $("<tr />").append(
        $("<td />").text(id),
        $("<td />").append($("<input />").val(name)),
        $("<td />").append($("<input />").val(phone)),
        $("<td />").append($("<input />").val(addr)),
        $("<td />").append($("<input />").val(resp)),
        $("<td />").append(
          $("<button>수정</button>").on("click", updateMemberFnc)
        )
      );
      tr.replaceWith(nTr);
    });
    tr.append(
      $("<td />").text(member.memberId),
      $("<td />").text(member.memberName),
      $("<td />").text(member.memberPhone),
      $("<td />").text(member.memberAddr),
      $("<td />").text(member.responsibility),
      $("<td />").append(
        $("<button>삭제</button>")
          .attr("mid", member.memberId)
          .on("click", deleteMember)
      )
    );
    return tr;
  }

  function deleteMember(ev) {
    if (window.confirm("삭제하시겠습니까?") == false) {
      return;
    }

    let user = $(ev.target).attr("mid");
    $.ajax({
      url: "removeMember.do",
      data: { id: user },
      success: function (result) {
        if (result.retCode == "Success") {
          $(ev.target).closest("tr").remove();
        } else {
          alert("삭제 중 오류 발생");
        }
      },
      error: function (reject) {
        console.log(reject);
      },
    });
  }

  function updateMemberFnc(e) {
    let id = $(e.target).closest("tr").children().eq(0).text();
    let name = $(e.target)
      .closest("tr")
      .children()
      .eq(1)
      .children()
      .eq(0)
      .val();
    let phone = $(e.target)
      .closest("tr")
      .children()
      .eq(2)
      .children()
      .eq(0)
      .val();
    let addr = $(e.target)
      .closest("tr")
      .children()
      .eq(3)
      .children()
      .eq(0)
      .val();
    let rsps = $(e.target)
      .closest("tr")
      .children()
      .eq(4)
      .children()
      .eq(0)
      .val();

    let formData = new FormData();
    formData.append("mid", id);
    formData.append("mname", name);
    formData.append("mphone", phone);
    formData.append("maddr", addr);
    formData.append("rsps", rsps);
    formData.append("from", "manager");

    $.ajax({
      url: "modMember.do",
      method: "post",
      data: formData,
      contentType: false,
      processData: false,
      success: function (result) {
        // 처리된 정보를 화면에 생성
        if (result.retCode == "Success") {
          $(e.target).closest("tr").replaceWith(makeRow(result.member));
        } else {
          alert("입력 중 오류 발생!");
        }
      },
      error: function (reject) {
        console.log(reject);
      },
    });
  }
});
