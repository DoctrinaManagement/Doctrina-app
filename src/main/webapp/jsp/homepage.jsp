<html>
<head>
    <title>DOCTRINA | Home Page</title>
    <link rel="stylesheet" href="../CSS/homepage.css"/>
    <link href="https://fonts.googleapis.com/css?family=Lato|Mirza|Oleo+Script" rel="stylesheet">
    <link rel="shortcut icon" href="../IMAGES/D-logo.png"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../JS/homepage.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/2.0.0/handlebars.js"></script>
    
</head>

	<style>
        .a{display:none;}
    </style>
    
    <script>
        
        function notification_function(a){
            console.log(a);
        }
        
        var webSocket;
         function signOut() {
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
              console.log('User signed out.');
              location.href = "/";
            });
        }
        
        window.fbAsyncInit = function() {
        
        FB.init({
          appId      : '1761766334129049',
          cookie     : true,
          xfbml      : true,
          version    : 'v2.10'
        });
          
        FB.AppEvents.logPageView();   
          
      };
    function logout() {
        FB.getLoginStatus(function(ret) {
            if(ret.authResponse) {

                FB.logout(function(response) {
                   location.href = "/";
                });
            } 
        });
    }
    
    var onloaded = function() {
    	socket();
    	//myclassroom();
    }

    var socket = function() {
    
        document.getElementById("profile_image").style.background = "url('<%=session.getAttribute("image")%>') 100%/100% 100%";
        if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
           return;
        }
        // Create a new instance of the websocket
        webSocket = new WebSocket("ws://127.0.0.1:8080/websocket/<%=session.getAttribute("user_id")%>");

        webSocket.onmessage = function(event){
            
            var notificationObj = JSON.parse(event.data)
            var notifi = notificationObj.notifications;
            notifi = notifi.reverse();
            var tempTemplete = document.getElementById("notification-templete").innerHTML;
            var templete  = Handlebars.compile(tempTemplete);
            notificationObj.notifications = notifi;
            console.log(templete(notificationObj));
            document.getElementById("notified").innerHTML= templete(notificationObj);
            //var key = Object.keys();
            var dot = false;
            for (i = 0; i < notificationObj.notifications.length; i++) {
                console.log(notificationObj.notifications[i]);
                var notification_count = 0;
                if(notificationObj.notifications[i].status) {
                    dot = true;
                    notification_count++;
                    document.getElementById(notificationObj.notifications[i].sender).style.background = "#EEEEEE";
                }
            }
            if(dot) {
                dot = false;
                document.getElementById("dotPoint").innerHTML = "<span class='dot'>"+notification_count+"</span>";
            }
        };
        var interval = setInterval(function() {if (webSocket.readyState === webSocket.OPEN) {
                var msg = "<%=session.getAttribute("user_id")%>";
                webSocket.send(msg);
                clearInterval(interval);
        }}, 1000);
        webSocket.onclose = function(event){
            
        };
        
    }
    </script>
<body onload="onloaded()">
    <div class="whole">
        
        <!-- Header -->
        
       <header>
            <img src="../IMAGES/D.gif" alt="D logo" />
            <div class="hdr">
                <div class="cls">
                    <label class="la1 select">All Classroom</label>
                    <label class="la2">My Classroom</label>
                </div>       
                <div class="header_div">
               <div>
                    <i class="fa fa-bell-o" aria-hidden="true"></i>
                    <p id="dotPoint"></p>
                    <div class="noti">
                        <p>Notifications</p>
                        <div class="noti_conts" id="notified"></div>
                    </div>
               </div>
               <div>
                   <div class="profile" id="profile_image"></div>
                   <div class="pro">
                       <img src="<%=session.getAttribute("image")%>" alt="profile" />
                       <div>
                           <p><%=session.getAttribute("name")%></p>
                           <p><%=session.getAttribute("role")%></p>
                           <%
                                if((session.getAttribute("login")+"").equals("fb")) {
                                    %><button><span id="fblogout" onclick="logout()"><a class="fb_button fb_button_medium"><span class="fb_button_text">Logout</span></a></span>
                                    </button><%
                                }
                                else{
                                    %><button onclick="signOut()">Logout</button><%
                                }
                            %>
                           <p>My Account</p>
                       </div>
                   </div>
               </div>
            </div>
            </div>
            <script id="notification-templete" type="text/x-handlebars-templete">

              {{#each notifications}}           
                 <div  onclick="notification_function('{{message}}')">
                    <img src="{{image}}"/>
                    <div class="contents">
                        <p><span>{{name}}</span> comment this</p>
                        <p >{{{message}}} </p>
                    </div>
                 </div>
              {{/each}}
           </script>
          <span style="clear:both"></span>
       </header> 
       
       <!--Main div-->
       
       <section class="box_div">
           
           <!-- Catagory of class-->
           
            <main class="all">
                
                <div>
                    <img src="../IMAGES/tags/maths.jpg" alt="Maths" />
                    <div>Maths</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/english.jpg" alt="English" />
                    <div>English</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/programming.jpg" alt="Programming" />
                    <div>Programming</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/designing.jpg" alt="Designing" />
                    <div>Designing</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/ai.jpg" alt="AI" />
                    <div>Artificial Inteligence</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/science.png" alt="Science" style="box-shadow: 0 0 30px rgba(0, 0, 0, 0.08) inset" />
                    <div>Science</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/history.jpg" alt="History" />
                    <div>History</div>
                </div>
                
                 <div>
                    <img src="../IMAGES/tags/animation.jpg" alt="Animation" />
                    <div>Animation</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/medical.jpg" alt="Medical" />
                    <div>Medical</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/engineer.jpg" alt="Engineering" />
                    <div>Engineering</div>
                </div>
                
                
                <div>
                    <img src="../IMAGES/tags/art.jpg" alt="Arts" />
                    <div>Arts</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/robotics.jpg" alt="Robotics" />
                    <div>Robotics</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/music.jpg" alt="Music" />
                    <div>Music</div>
                </div>
                 
                <div>
                    <img src="../IMAGES/tags/tamil.jpg" alt="Tamil" />
                    <div>Tamil</div>
                </div>
                
                <div>
                    <img src="../IMAGES/tags/economic.jpg" alt="Economic" />
                    <div>Economics</div>
                </div>
               
                <p style="clear: both"></p>
                
            </main>
           
           <!-- All classroom -->
           
           <div class="allclass">
               <!--<span class="back"><i class="material-icons">arrow_back</i> Back</span>-->
                <div class="title_div">
                   <div class="title">
                       <i class="fa fa-times-circle fa-2" aria-hidden="true"></i>
                       <label>Classroom Name</label>
                       <input type="text" required autofocus/>
                       <label>Description</label>
                       <textarea maxlength="110"></textarea>
                       <button type="submit" class="done_btn">Done</button>
                   </div>
                   <div class="crt_div">
                       <p>Create New Classroom</p>
                       <button type="submit" class="create_btn">+</button>
                   </div>
               </div>
           </div>
           
           <!--  My classroom  -->
           
           <div class="width_div">
              
           </div>
       </section>
    </div>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="176409898115-eoi5sggfanbiq08h1e6soi4vtcm30mgf.apps.googleusercontent.com">
    <div class="g-signin2 a" data-onsuccess="onSignIn"></div>
</body>
</html>