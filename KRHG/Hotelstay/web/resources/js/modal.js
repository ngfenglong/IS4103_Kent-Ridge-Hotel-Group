jQuery(document).ready(function($){
	//open popup
	$('.cd-popup-trigger').on('click', function(event){
		event.preventDefault();
		$('#modal').addClass('is-visible');
                console.log($('#modal'));
                console.log($('#modal').attr('class'));
	});
  
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
      console.log(event.target);
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
  });
  
  	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-closemodal') || $(event.target).is('.cd-popup') ) {
      console.log(event.target);
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
  });

    	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-confirm') || $(event.target).is('.cd-popup') ) {
      console.log("INMODAL:" + event.target.onclick);
      if($(event.target).attr("onclick") == "onClickNext(0)"){
        console.log("hello");
          var behaviorValue = 'yes';
          var typeValue = 'info';
          var el = document.createElement('p');
          el.classList.add('notification');
          var text = '';
          if($(event.target).attr("id")=="laundry"){
            text = "Your laundry request has been cancelled"
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
      
      }
      event.preventDefault();
			$(this).removeClass('is-visible');
		}
  });
  
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$('.cd-popup').removeClass('is-visible');
	    }
    });
});