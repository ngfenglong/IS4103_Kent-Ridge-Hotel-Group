
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
    $('#modal').delay( 800 ).addClass('is-visible');
    console.log($('#modal'));
    console.log($('#modal').attr('class'));
    
    
    return true;
	});
        
        //open popup
	$('.cd-popup-trigger').on('click', function(event){
            
		event.preventDefault();
    $('#modal').delay( 800 ).addClass('is-visible');
    console.log($('#formModal'));
    console.log($('#formModal').attr('class'));
    
    
    return true;
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
	$('.cd-popup-confirm').on('click', function(event){
		if( $(event.target).is('.cd-popup-confirm') || $(event.target).is('.cd-popup') ) {
      console.log($(event.target).attr("id").substring(8));
      console.log("number4");

          var behaviorValue = 'yes';
          var typeValue = 'info';
          var el = document.createElement('p');
          el.classList.add('notification');
          var text = '';
          if($(event.target).attr("id").substring(8)==="deleteHotel"){
            text = "Hotel has been deleted"
          } else if ($(event.target).attr("id").substring(8)==="deleteFeedback"){
            text = "Feedback has been deleted"
          } else if ($(event.target).attr("id").substring(8)==="deleteStaff"){
            text = "Staff has been deleted"
          }else if ($(event.target).attr("id").substring(8)==="deleteRoom"){
            text = "Room has been deleted"
          }else if ($(event.target).attr("id").substring(8)==="deleteFoodMenuItem"){
            text = "Food Menu Item has been deleted"
          }else if ($(event.target).attr("id").substring(8)==="deleteFoodOrder"){
            text = "Food Order has been deleted"
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