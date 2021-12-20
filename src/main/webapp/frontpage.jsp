
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Online EPAM Art Gallery</title>
  <link rel="stylesheet" href="WEB-INF/css/frontpage.css">
  <script type="text/javascript" href="js/frontpage.js"></script>
</head>
<body>
<div style="text-align:center">
  <h2>Online EPAM Art Gallery</h2>
  <div class="navbar">
    <a href="frontpage.jsp"> Home (start page)</a>
    <a href="WEB-INF/views/Paintings.jsp">Paintings</a>
    <a href="exhibitions.jsp">Exhibitions</a>
    <a href="About_us.jsp">About</a>
  </div>

  <tr>
    <th><small>
      <a href="loginPage.jsp"> <input type="submit" name="login" value="Login"></a>
    </small>

    <th><small>
      <a href="signup.jsp"> <input type="submit" name="registration" value="Registration"></a>
    </small>
      <br>
      <br>

      <style>
        * {box-sizing:border-box}
        body {
          font-family: Verdana,sans-serif;
        }
        .mySlides {
          display:none;
          /* height: 1vw !important;  */
        }

        /* Slideshow container */
        .slideshow-container {
          max-width: 1000px;
          max-height: 5000px;
          position: relative;
          margin: auto;
        }

        /* Caption text */
        .text {
          color: #f2f2f2;
          font-size: 15px;
          padding: 8px 12px;
          position: absolute;
          bottom: 8px;
          width: 100%;
          height: 100%;
          text-align: center;
        }

        /* Number text (1/3 etc) */
        .numbertext {
          color: #f2f2f2;
          font-size: 12px;
          padding: 8px 12px;
          position: absolute;
          top: 0;
        }

        /* The dots/bullets/indicators */
        .dot {
          height: 15px;
          width: 15px;
          margin: 0 2px;
          background-color: #bbb;
          border-radius: 50%;
          display: inline-block;
          transition: background-color 0.6s ease;
        }

        .active {
          background-color: #717171;
        }

        /* Fading animation */
        .fade {
          -webkit-animation-name: fade;
          -webkit-animation-duration: 1.5s;
          animation-name: fade;
          animation-duration: 1.5s;
        }

        @-webkit-keyframes fade {
          from {opacity: .4}
          to {opacity: 1}
        }

        @keyframes fade {
          from {opacity: .4}
          to {opacity: 1}
        }

        /* On smaller screens, decrease text size */
        @media only screen and (max-width: 300px) {
          .text {font-size: 11px}
        }
      </style>


      <!-- <h2>Automatic Slideshow</h2>
      <p>Change image every 2 seconds:</p>
       -->
      <div class="slideshow-container">

        <div class="mySlides fade">
          <!--   <div class="numbertext">1 / 3</div>
           -->  <img src="https://artlogic-res.cloudinary.com/w_2166,h_1185,c_fill,f_auto,fl_lossy/ws-halesgallery/usr/images/artists/group_images_override/items/5f/5f415b33b7cb4386a8ec5ffeb0aa772b/anthony-cudahy-seeking-a-patter-2021-oil-on-canvas-91.4-x-121.9-cm-a_cud0054-photo-by-jsp-art-photography.jpg" style="width:90%;height:40vw">

        </div>

        <div class="mySlides fade">
          <!-- <div class="numbertext">2 / 3</div> -->
          <img src="https://www.cobosocial.com/wp-content/uploads/2019/07/c999c0a2a6e0c85a4c1462ede1b74cc3-e1564717054634.jpeg" style="width:90% ;height:30vw">

        </div>

        <div class="mySlides fade">
          <!-- <div class="numbertext">3 / 3</div> -->
          <img src="https://www.cobosocial.com/wp-content/uploads/2019/07/a3529a3c53f6ee7b552f9ffd3a5128cd-e1564717409863.jpeg" style="width:90%;height:30vw">

        </div>

      </div>
      <br>

      <div style="text-align:center">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
      </div>

      <script>
        var slideIndex = 0;
        showSlides();

        function showSlides() {
          var i;
          var slides = document.getElementsByClassName("mySlides");
          var dots = document.getElementsByClassName("dot");
          for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
          }
          slideIndex++;
          if (slideIndex > slides.length) {slideIndex = 1}
          for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
          }
          slides[slideIndex-1].style.display = "block";
          dots[slideIndex-1].className += " active";
          setTimeout(showSlides, 2000); // Change image every 2 seconds
        }
      </script>
</body>
</html>