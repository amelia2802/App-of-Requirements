/* Set the width of the side navigation to 250px */
function openNav() {
    document.getElementById("sidebar-wrapper").style.width = "30%";
  }
  
  /* Set the width of the side navigation to 0 */
  function closeNav() {
    document.getElementById("sidebar-wrapper").style.width = "0";
}

let slideIndex = 0;
showSlides();

function showSlides() {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
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
  setTimeout(showSlides, 8000); // Change image every 2 seconds
}

const fName = document.getElementById("fname");

fName.innerHTML+=user_fname