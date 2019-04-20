var check = false;

function changeVal(el) {
    var qt = parseFloat(el.parent().children(".qt").html());
    console.log(qt);
    var singleprice = el.parent().children(".price").html();
    var price = parseFloat(singleprice.trim().substring(1,singleprice.length));

    console.log(singleprice.trim().substring(1,singleprice.length));
    var eq = Math.round(price * qt * 100) / 100;
    console.log(eq);

    el.parent().children(".full-price").html( "$" + eq );

    changeTotal();			
}

function changeTotal() {
    var price = 0;

    $(".full-price").each(function(index){
      var temp = $(".full-price").eq(index).html().trim();
      price += parseFloat(temp.substring(1,temp.length));
      console.log($(".full-price").eq(index).html().trim());
      console.log(price);
    });
    console.log("total -" + price);
    price = Math.round(price * 100) / 100;
    console.log("total -" + price);
    var tax = Math.round(price * 0.07 * 100) / 100;
    console.log("total - " + tax);
    var fullPrice = Math.round((price + tax) *100) / 100;
    console.log("total - " + fullPrice);

    if(price === 0) {
      fullPrice = 0;
    }

    $(".subtotal span").html(price);
    $(".tax span").html(tax);
    $(".total span").html(fullPrice);
}

$(document).ready(function(){
    $(".remove").click(function(){
      var el = $(this);
      console.log(el);
      el.parent().parent().addClass("removed");
      window.setTimeout(
        function(){
          el.parent().parent().slideUp('fast', function() { 
            el.parent().parent().remove(); 
            console.log(el);
            if($(".product").length === 0) {
              if(check) {
                $("#site-footer").html("<p>Thank you for dining with KRHG Room Service. We have received your order. Your food will be delivered to you within 30 minutes.</p>");
              } else {
                $("#cart").html("<h1>No Food Item selected! Please select your items from the menu</h1>");
              }
            }
            changeTotal(); 
          });
        }, 200);
    });

    $(".qt-plus").click(function(){

      console.log($(this).parent().children(".full-price"));
      $(this).parent().children(".qt").html(parseInt($(this).parent().children(".qt").html()) + 1);

      $(this).parent().children(".full-price").addClass("added");
      console.log($(this).parent().children(".full-price"));
      var el = $(this);
      console.log(el);
      window.setTimeout(function(){el.parent().children(".full-price").removeClass("added"); changeVal(el);}, 150);
    });

    $(".qt-minus").click(function(){

      child = $(this).parent().children(".qt");

      if(parseInt(child.html()) > 1) {
        child.html(parseInt(child.html()) - 1);
      }

      $(this).parent().children(".full-price").addClass("minused");

      var el = $(this);
      window.setTimeout(function(){el.parent().children(".full-price").removeClass("minused"); changeVal(el);}, 150);
    });

    window.setTimeout(function(){$(".is-open").removeClass("is-open")}, 1200);

    $(".os-btn").click(function(){
      check = true;
      $(".remove").click();
    });
});