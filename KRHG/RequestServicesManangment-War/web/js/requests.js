function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks, name, element;
  
    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        if(tabcontent[i].id != cityName){
            tabcontent[i].style.display = "none";
            console.log("in here 1");
        } else {
            tabcontent[i].style.display="block";
        }
    }
  
    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
  
    // Show the current tab, and add an "active" class to the button that opened the tab
    // element = document.getElementById(cityName).style.display = "block";
    // console.log(document.getElementById(cityName));
    evt.currentTarget.className += " active";
}

/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function dropdown() {
    document.getElementById("myDropdown").classList.toggle();
    console.log(document.getElementById("myDropdown").classList);
  }
  
  // Close the dropdown menu if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    }
  }

  $(".dropdown-menu li a").click(function(){
    $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
    $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
  });

  document.getElementsByClassName("dropdown-menu").click(
      function(){
          document.getElementsByTagName("span").getElementsByClassName("caret").display(
              
          )
      }
  )
