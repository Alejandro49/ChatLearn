<%@ page import="principal.Usuario" %>
<%@ page import="principal.UsuarioDao" %>
<%@ page import="java.sql.SQLException" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>ChatLearn</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="../css/animate.css"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../css/owl.carousel.css"/>
    <link rel="stylesheet" type="text/css" href="../css/owl.theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/owl.transitions.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,100,200,300,500,600,800,900' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oleo+Script+Swash+Caps:400,700' rel='stylesheet' type='text/css'>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
    }

    Usuario user = new Usuario();
    user.setNombreUsuario(userName);

    UsuarioDao userDao=new UsuarioDao();
    try {
        user=userDao.getPerfil(user);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }




%>


<div class="main-header" id="main-header">
    <nav class="navbar mynav navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <a class="navbar-brand" href="#">ChatLearn</a> </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li ><a href="dashboard.html">Inicio</a></li>
                    <li class="active"><a href="#banner">Mi Perfil</a></li>
                    <li><a href="buscarchat.html">Buscar Chat</a></li>
                    <li><a href="anunciarme.html">Anunciarme</a></li>
                    <li><a href="contactos.html">Contactos</a></li>
                    <li><a href="configuracion.html">Configuracion</a></li>
                    <li><a href="logout.html">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="banner" id="banner">
    <div class="bg-overlay">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="banner-text">
                        <h2>Bienvenido a tu perfil:  <%=userName %><br> </h2>
                        <img src="../images/<%=user.getFoto()%>" alt="Imagen no encontrada" onerror="../images/newUser.jpg" width="100" height="100">
                        <br>
                        <br>
                        <p>Interes 1: <%=user.getIntereses().get(0)%></p>
                        <p>Interes 2: <%=user.getIntereses().get(1)%></p>
                        <p>Interes 3: <%=user.getIntereses().get(2)%></p>
                        <p>Interes 4: <%=user.getIntereses().get(3)%></p>
                        <p>Interes 5: <%=user.getIntereses().get(4)%></p>
                        <p>Lengua Materna: <%=user.getLengua()%></p>
                        <p>Calificacion: <%=user.getCalificacion()%></p>
                        <a href="#" class="banner-button"> Editar perfil</a> </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Portfolio -->


<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-4"> <span class="copyright">Copyright &copy; ChatLearn 2020</span> </div>
            <div class="col-md-4">
                <ul class="list-inline social-buttons">
                    <li><a href="#"><i class="fa fa-twitter"></i></a> </li>
                    <li><a href="#"><i class="fa fa-facebook"></i></a> </li>
                    <li><a href="#"><i class="fa fa-linkedin"></i></a> </li>
                </ul>
            </div>
            <div class="col-md-4">

            </div>
        </div>
    </div>

</footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/owl.carousel.min.js"></script>
<script type="text/javascript" src="js/jquery.countTo.js"></script>
<script type="text/javascript" src="js/jquery.waypoints.min.js"></script>
<script>
    $(document).ready(function() {

        $("#owl-demo").owlCarousel({

            navigation : false, // Show next and prev buttons
            slideSpeed : 500,
            autoPlay : 3000,
            paginationSpeed : 400,
            singleItem:true

            // "singleItem:true" is a shortcut for:
            // items : 1,
            // itemsDesktop : false,
            // itemsDesktopSmall : false,
            // itemsTablet: false,
            // itemsMobile : false

        });

    });

    /*$('.timer').each(count);*/
    jQuery(function ($) {
        // custom formatting example
        $('.timer').data('countToOptions', {
            formatter: function (value, options) {
                return value.toFixed(options.decimals).replace(/\B(?=(?:\d{3})+(?!\d))/g, ',');
            }
        });


        // start all the timers
        $('#testimonials').waypoint(function() {
            $('.timer').each(count);
        });

        function count(options) {
            var $this = $(this);
            options = $.extend({}, options || {}, $this.data('countToOptions') || {});
            $this.countTo(options);
        }
    });


    $(document).ready(function(){
        // Add smooth scrolling to all links in navbar + footer link
        $(".navbar a, footer a[href='#myPage']").on('click', function(event) {

            // Prevent default anchor click behavior
            event.preventDefault();

            // Store hash
            var hash = this.hash;

            // Using jQuery's animate() method to add smooth page scroll
            // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 900, function(){

                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        });
    })
</script>
</body>
</html>