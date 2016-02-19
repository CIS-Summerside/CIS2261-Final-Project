<?php
/**
 * Created by PhpStorm.
 * User: Anson Carmody
 * Date: 2016-02-06
 * Time: 9:05 AM
 * Description: Web page that displays all files that are available for download on TFUS as well as any information relevant to TFUS.
 * Theme: Based on open source theme bootstrap grayscale from http://startbootstrap.com/template-overviews/grayscale/
 */
?>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Temporary File Hosting Service</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/TFHS_Style.css" rel="stylesheet">

    <!-- Hover css for custom hover animations-->
    <link href= "css/hover.css" rel="stylesheet" media="all">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

<!-- Navigation -->
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand page-scroll" href="#page-top">
                <i class="fa fa-cloud"></i> T<span class="light extraSmall">emporary</span>F<span class="light extraSmall">ile</span>H<span class="light extraSmall">osting</span>S<span class="light extraSmall">ervice</span>
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
            <ul class="nav navbar-nav">
                <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li>
                    <a class="page-scroll" href="#files">Files</a>
                </li>
                <li>
                    <a class="page-scroll" href="#about">About</a>
                </li>
                <li>
                    <a class="page-scroll" href="#download">App Download</a>
                </li>
                <li>
                    <a class="page-scroll" href="#contact">Contact</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Intro Header -->
<header class="intro">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <h1 class="brand-heading">Temporary File Hosting Service</h1>
                    <p class="intro-text">Upload and Download Temporary Files</p>
                    <a class="white" href="#files"><i class="fa fa-4x fa-angle-double-down animated"></i></a>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- File Gallery Section -->
<section id="files" class="container content-section text-center">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>File Gallery</h2>
            <p>Files listed below are available for download</p>
            <hr class="TFUS"><br>

            <!-- Collecting files from API and displaying as html-->
            <?php
            //Get request through to an API
            $json_string = file_get_contents("http://localhost:8080/api/files/list");
            $parsed_json = json_decode($json_string, true);

            //THIS ECHOS OUT AN ARRAY OF FILES FROM GET REQUEST FOR TESTING PURPOSES
//           echo '<pre>';
//            print_r($parsed_json);
//
//            //loop and output File Name, Size and Download Link (http://localhost:8080/api/files/download/WhateverCodeIs
//            foreach($parsed_json['data'] as $file) {
//                echo 'File Name: ' . $file['originalName'] . '<br />';
//               echo 'File Size: ' . $file['fileSize'] . '<br />';
//                echo 'DownloadLink: ' .$file['downloadCode']. '<br />';
//               $fileName = pathinfo($file['originalName']);
//                $fileType = $fileName['extension'];
//                echo 'File Type: '. $fileType . '<br />';
//                //echo $fileType = substr($file['originalName'], -3);
//            }

            //create imgur style gallery of images as hyperlinks
            ?>
        </div>
        <!-- Page Content -->
        <div class="container">

            <div class="row">

                <?php
                //create gallery based on array size
                foreach($parsed_json['data'] as $file) {
                    //determine file type
                    $fileName = pathinfo($file['originalName']);
                    $fileType = $fileName['extension'];

                    $fileTitle = $file['originalName'];
                    $fileSizeString = "";

                    //determine file size and label appropriately
                    if($file['fileSize'] >= 1000 && $file['fileSize'] < 1000000){
                        $fileSizeString = ($file['fileSize']/1000). " KB";
                    }
                    else if($file['fileSize'] >= 1000000 && $file['fileSize'] < 100000000 ){

                        $fileSizeString = ($file['fileSize']/1000000). " MB";
                    }
                    else if($file['fileSize'] >= 100000000 )
                        $fileSizeString = ($file['fileSize']/100000000). " GB";

                    //assign correct image to file type
                    switch(strtolower($fileType)){
                        case "doc":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-22.png";
                            break;
                        case "docx":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-21.png";
                            break;
                        case "ps":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-02.png";
                            break;
                        case "ai":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-03.png";
                            break;
                        case "id":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-04.png";
                            break;
                        case "xls":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-23.png";
                            break;
                        case "pptx":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-20.png";
                            break;
                        case "css":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-19.png";
                            break;
                        case "m4a":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-31.png";
                            break;
                        case "mp4":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-15.png";
                            break;
                        case "mp3":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-25.png";
                            break;
                        case "avi":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-14.png";
                            break;
                        case "pdf":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-12.png";
                            break;
                        case "jpg":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-27.png";
                            break;
                        case "png":
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-26.png";
                            break;
                        default:
                            $fileImage = "img/thumbnail-images/Icons/file_type_icons_flat_-13.png";
                    }
                    //Output thumbnail of file with file title and download link
                    echo '<div class="col-lg-3 col-md-4 col-xs-6 thumb hvr-grow" >
                            <a class="thumbnail darkBack" href="http://localhost:8080/api/files/download/'.$file['downloadCode'].'">
                                <div class="caption">
                                    <p>'.$fileTitle.'</p>
                                    <p>'.$fileSizeString.'</p>
                                </div>
                                    <img class="img-responsive" src="'.$fileImage .'" alt="">
                            </a>
                        </div>';
                    }
                ?>
            </div>
    </div>
</section>

<!-- About Section -->
<section id="about" class="container content-section text-center">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>About Temporary File Hosting Service</h2>
            <hr class="TFUS"><br>
            <p><strong>Temporary File Hosting Service (TFHS)</strong> is an application that allows users to upload and download files with temporary life times</a>. Users are able to upload files with specific life times and privacy settings.</p>
            <p>TFHS allows users to share files temporarily allowing space to be freed up and reused for new files.</p>
        </div>
    </div>
</section>

<!-- Download Section -->
<section id="download" class="content-section text-center">
    <div class="download-section">
        <div class="container">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Download TFHS</h2>
                <p>In order to upload files to TFHS you must download the application and register for an account</p>
                <a href="#" class="btn btn-default btn-lg">Download TFHS App</a>
            </div>
        </div>
    </div>
</section>

<!-- Contact Section -->
<section id="contact" class="container content-section text-center">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>Contact TFHS</h2>
            <hr class="TFUS"><br>
            <p>Please email us if you have any questions or concerns regarding the Temporary File Hosting Service</p>
            <p><a href="mailto:feedback@startbootstrap.com">skyllama@yandex.com</a>
            </p>
        </div>
    </div>
</section>



<!-- Footer -->
<footer>
    <div class="container text-center">
        <p>Copyright &copy; TFHS 2016</p>
    </div>
</footer>

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="js/jquery.easing.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/grayscale.js"></script>

<!-- Thumbnail Title JavaScript -->
<script src="js/thumbnailTitle.js"></script>


</body>

</html>
