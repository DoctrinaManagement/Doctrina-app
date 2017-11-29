$(document).ready(function(){
    
    // ---------------------------------- onload -----------------------------------
    
    $(".main2_div,.width_div,.allclass").css("display","none");
   
    //  ------------------------------- classroom create --------------------------------------
    
    $(".create_btn").click(function(){
       $(".crt_div").css("top","-275px");
    });
   
    var num=-1,n=0;limit=0;
    // var array=["#e8aaf3","#73efbb","#ff89a9","#c1f97f","#fff27c","#ffa3a3"];
    var array=["#73efbb","#fff27c","#ff89a9","#c1f97f","#ffa3a3","#e8aaf3"];
    $(".done_btn").click(function(){
        num++;n++;limit++;
        $(".title_div").before("<div class='new'><div class='newclass' style='background:"+array[num]+"' onclick='hide()'><img src='../IMAGES/classs.png'><p id='name"+n+"'></p><button type='submit'>Join</button></div><i class='info fa fa-info' aria-hidden='true'></i><i class='fa fa-trash-o dlt' aria-hidden='true' title='Delete'></i></div>"); 
        $(".crt_div").css("top","0");
        if(num==5){
            num=-1;
        }
        if(limit==12){
            $(".title_div").css("display","none");
        }
        var txt=$(".title input:text").val();
        $("#name"+n).text(txt);
            $(".title input").val('');
            $(".title textarea").val('');
    });
    // -------------------------------- delete classroom---------------------------------
    
    $(".dlt").click(function(){
        $(this).css("display","none");
    });

    // ------------------------------ notification and dropdowns --------------------------- 
    i=0;
    $(".header_div i").click(function(){
       if(i==0){
           $(".noti").css({"left":"-234px"}) ;
           $(".pro").css({"right":"-250px"}) ;
           i++;
       }
       else{
           $(".noti").css({"left":"234px"}) ;
            i=0;
       }
    });
    $(".box_div").click(function(){
        $(".noti").css({"left":"234px"}) ;
    });
    j=0;
    $(".profile").click(function(){
       if(j==0){
           $(".pro").css({"right":"19px"}) ;
            $(".noti").css({"left":"234px"}) ;
           j++;
       }
       else{
           $(".pro").css({"right":"-250px"}) ;
            j=0;
       }
    });
    $(".box_div").click(function(){
        $(".pro").css({"right":"-250px"});
    });
    $(".title i").click(function(){
        $(".crt_div").css("top","0px");
    });
    $(".dlt").click(function(){
        (".new").css("display","none"); 
    });
    
    // ---------------------------------- video ----------------------------------------
    
    $(".video_div div").click(function(){
        $(".video_div").css({"padding":"0"});
        $(".video_div section").css({"background":"#ccc","display":"block","position":"fixed"});
    });
    
    $(".close").click(function(){
        $(".video_div section").css({"display":"none"});
        $(".video_div").css({"padding":"20px 61px"});
    });
    
    l=0;
    $("nav ul li:last-child").click(function(){
        l++;
        if(l==1){
            $(".drop").css({"display":"block"});
        }
        else{
            $(".drop").css({"display":"none"});
            l=0;
        }
    });
    
    $(".la2").click(function(){
        $(".la2").addClass("select");
        $(".la1").removeClass("select");
        $(".all").css("display","none");
        $(".allclass").css("display","none");
        $(".width_div").css("display","block");
    });
    
    $(".la1").click(function(){
        $(".la1").addClass("select");
        $(".la2").removeClass("select");
        $(".all").css("display","block");
        $(".allclass").css("display","none");
        $(".width_div").css("display","none");
    });
    // var a=prompt("enter the number's of classrooms");
    var a=6;
    var array=["#73efbb","#fff27c","#ff89a9","#c1f97f","#ffa3a3","#e8aaf3"];
    for(i=0;i<=a-1;i++){
        $(".width_div").append("<div class='new'><div class='newclass' style='background:"+array[i]+"' onclick='hide()'><img src='../IMAGES/classs.png'><p>asdfghjkl</p><button type='submit'>Join</button></div><i class='info fa fa-info' tabindex='0' onclick='des(this)'  aria-hidden='true'></i> <i class='fa fa-trash-o dlt' aria-hidden='true' title='Delete'></i> <div class='des'  tabindex='1'> <p>Created by</p> <p>Selva</p> <div class='show_des'></div> </div> </div>"); 
    }
    
      // --------------------------------------- join ----------------------------------------
    
    $(".new>.newclass button").click(function(){
       $(this).text("Joined");
       $(this).css("background","white");
    });
    
    // -------------- go to create class rooms ----------------------------
    
    $(".all>div").click(function(){
       $(".all").css("display","none");
       $(".allclass").css("display","block");
    });
    $("header>img").click(function(){
        $(".la1").addClass("select");
        $(".la2").removeClass("select");
        $(".allclass").css("display","none");
        $(".all").css("display","block");
    });
});  


