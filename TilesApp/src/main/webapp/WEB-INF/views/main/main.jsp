<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"
/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
  $(document).ready(function () {
    $(".slider").bxSlider({
      mode: "fade",
      captions: true,
    });
  });
</script>

<div class="slider" align="center">
  <div><img width="850px" src="images/벚꽃.jpg" /></div>
  <div><img width="850px" src="images/빗물.jpg" /></div>
  <div><img width="850px" src="images/하늘.jpg" /></div>
</div>
