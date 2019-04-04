
jQuery(document).ready(function($){

	// //open popup
	// $('.cd-popup-trigger').on('click', function(event){
  //   event.preventDefault();
  //   console.log("number1");
  //   $('#modalVH').addClass('is-visible');
  // });
  
  //open popup
	$('.cd-popup-trigger').on('click', function(event){
		event.preventDefault();
    $('.cd-popup').addClass('is-visible');
    
    return false;
	});
  
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
      console.log(event.target);
      console.log("number2");
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
  });
  
  	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-closemodal') || $(event.target).is('.cd-popup') ) {
      console.log(event.target);
      console.log("number3");console.log("number1");
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
  });

    	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-confirm') || $(event.target).is('.cd-popup') ) {
      console.log("number4");
          var behaviorValue = 'yes';
          var typeValue = 'info';
          var el = document.createElement('p');
          el.classList.add('notification');
          var text = '';
          if($(event.target).attr("id")=="deleteHotel"){
            text = "You have deleted the hotel"
          } else if ($(event.target).attr("id")=="housekeeping"){
            text = "Your housekeeping request has been cancelled"
          }

          switch (typeValue) {
              case "success":
                  el.classList.add('is-success');
                  break;
              case "error":
                  el.classList.add('is-danger');
                  break;
              case "warning":
                  el.classList.add('is-warning');
                  break;
              default:
                  el.classList.add('is-primary');
                  break;
          }
          if (behaviorValue === 'yes') {
              el.setAttribute('data-close', 'self');
          } else {
              innerHTML += '<button class="delete" type="button">Close</button>';
          }
          el.innerHTML = text;
          document.querySelector('section').appendChild(el);
      
     
      event.preventDefault();
			$(this).removeClass('is-visible');
		}
  });
  
});