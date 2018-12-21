<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Promotion Management</title>

<link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/img/Logo_DIGI-TEXX_2013-2.jpg"  type="image/x-icon"/>



  <!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="fonts/css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
  
  
<link rel="stylesheet" href="css/jquery.iviewer.css"/>
<link rel="stylesheet" href="css/pretty-split-pane.css"/>
<link rel="stylesheet" href="css/split-pane.css"/> 
<link href="css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="js/docs-js-jquery-1.7.1.min.js"></script>	
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script src="js/bootstrap-datepicker.min.js"></script>


<script src="js/jquery.dataTables.min.js"></script>
	<!-- LOOKUP DATA -->
<link href="css/awesomplete.css" type="text/css" rel="stylesheet">
<script src="js/awesomplete.js"></script>

 
<style type="text/css">
.tooltip {
	font-size: 10pt;
	background-color: #FFFFCC;
	border: 1px solid black;
	padding: 2px
}
.viewer {
    height: 100%;
   /*  overflow: hidden!important; */
    position: relative;
    width: 100%;
}
body {
    background-color: #c6dfff;
    color: #666;
    font-family: Arial,Verdana,sans-serif;
    font-size: 12px;
    line-height: 1.3em;
    margin: 0;
}
.span_left{
   padding-left: 23px;
   font-style: italic;
}

#header {
    background-color: #fff;
    border-radius: 10px;
    border-top: 1px solid #b5c8e0;
    box-shadow: 0 0 8px #b6bdc4;
    height: 100px !important;
    margin: 0 auto;
    overflow: hidden;
    padding: 0 20px 20px;
    width: 99%;
}

#center {
    background-color: #fff;
    border: 1px solid #b5c8e0;
    border-radius: 10px;
    box-shadow: 0 0 8px #b6bdc4;
    margin: 0 auto;
    min-height: 607px;
    overflow: hidden;
   
    width: 99%;
}
#footer {
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 10px;
    clear: both;
    height: 100px;
    margin: 4px auto 0;
    padding-left: 20px;
    padding-right: 20px;
    width: 99%;
}

.loginTitle {
    color: blue;
    font-size: 24px;
    font-weight: bold;
    margin-top: 100px;
    text-align: center;
}

.login_form {
    border: 1px solid #becfe6;
    border-radius: 10px;
    height: 134px;
    margin-left: 261px !important;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-top: 20px;
    width: 444px;
    margin-top: 70px;
}
.container {    
    padding-top: 7px!important;
    padding-bottom: 7px!important;
    padding-left: 7px!important;
    padding-right: 7px!important;
}
.index_2_1 {
    border: 1px solid #becfe6;
    clear: both;
    height: 30px;
    margin-bottom: 10px;
}

.index_2_1 {
    border: 1px solid #becfe6;
    clear: both;
    height: 30px;
    margin-bottom: 10px;
    width: 375px;
}

.index_2_1_1 {
    border-right: 1px solid #becfe6;
    float: left;
    font-size: 14px;
    font-weight: bold;
    height: 21px;
    padding-left: 10px;
    padding-top: 10px;
    width: 113px;
}

.index_2_1_1 {
    border-right: 1px solid #becfe6;
    float: left;
    font-size: 14px;
    font-weight: bold;
    height: 21px;
    padding-left: 10px;
    padding-top: 10px;
    width: 113px;
}
.buttonSubmit {
    background: -moz-linear-gradient(center top , #ededed 5%, #5a86e0 100%) repeat scroll 0 0 #ededed;
    border: 1px solid #dcdcdc;
    border-radius: 6px;
    box-shadow: 0 1px 0 0 #ffffff inset;
    color: #183ead !important;
    display: inline-block;
    font-family: arial;
    font-size: 15px;
    font-style: normal;
    font-weight: bold;
    height: 33px;
    margin-left: 125px;
    text-align: center;
    text-decoration: none;
    text-indent: 0;
    text-shadow: 1px 1px 0 #ffffff;
    width: 120px;
}
.th_table_15{
width: 15%!important;
text-align: center;
}
#Node1,#Node2,#Node3,#Node4,#Node5,#Node6,#Node7,#Node8,#Node9,#Node10,#Node11,#Node12,#Node13,#Node14,#Node15,#Node16,#Node17,#Node18,#Node19,#Node20{
font-weight: 100;
}
#Node21,#Node22,#Node23,#Node24,#Node25,#Node26,#Node27,#Node28,#Node29,#Node30,#Node31,#Node32,#Node33,#Node34,#Node35,#Node36,#Node37,#Node38,#Node39,#Node40{
font-weight: 100;
}
#Node41,#Node42,#Node43,#Node44,#Node45,#Node46,#Node47,#Node48,#Node49,#Node50,#Node51,#Node52,#Node53,#Node54,#Node55,#Node56,#Node57,#Node58{
font-weight: 100;
}
.dataTables_filter{
text-align: right;
}
.form_search{
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 4px;
    clear: both;
    margin-top: 10px;
    padding-top: 10px;
}
.detail_data_table , .FileUpLoad_table ,.cumuluspro_table ,.UserManulife_table{
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 4px;
    clear: both;
    margin-top: 10px;
    padding-top: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
}
.detail_data_table_final{
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 4px;
    clear: both;
    margin-top: 10px;
    padding-top: 10px;
    padding-left: 20px;
    padding-right: 20px;
    padding-bottom: 10px;
}

.dilog_table{
 border: 1px solid #becfe6;
 font-size: 11px;
}
body {
	font-size: 12px;
	
}
.padding_left{
   padding-left: 20px;
}
</style>
<style type="text/css">
.tooltip {
	position: absolute;
	display: none;
}
.w3-btn, .w3-btn:link, .w3-btn:visited {
    background-color: #4caf50;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
    color: #ffffff;
    font-weight: bold;
    font-size: 14px;
    text-align: center;
}
.table-th{
text-align: center;
}
.class-font_14{
   font-size: 14px;
   font-weight: bold;
}
.search_cbb_Location ,.search_cbb_Box_No{
font-weight:bold;
font-size:14px;
width: 200px;
height: 31px;
}
.title_search_cbb_Location ,.title_search_cbb_Box_No{
    text-align: left;
    padding-top: 15px!important;
    padding-right: 0px;
    width: 112px;
    font-weight: bold;font-size: 14px
}
.dataTables_scrollHead{
height: 36px;
background-color: #4caf50;
}
table.dataTable {
    border-collapse: collapse!important;
    border-spacing: 0;
}
.title_width_140{
	float: left;
    font-size: 13px;
    font-weight: bold;
    padding-left: 25px;
    padding-top: 10px;
    width: 140px;
}
.value_width_170{
	float: left;
    font-size: 13px;
    height: 33px;
    width: 161px;
}
.button_width_320{
	float: left;
    font-size: 13px;
    height: 27px;
    width: 300px;
    margin-left: 15px;
}
.button_width_boxNo{
	 float: left;
    font-size: 13px;
    height: 33px;
    margin-left: 67px;
    margin-top: 5px;
    width: 182px;
}
.tab-content{
min-height: 650px;
}
 /* th, td { 
white-space: nowrap;
}   */

.btn-edit ,.btn-create ,.btn-cancel{
    background-color: #5eba5e !important;
    border-color: #4cae4c !important;
    height: 34px;
    width: 100px;
}
.dlg_name{
    background-color: #fff;
    background-repeat: repeat-x;
    border-radius: 1px;
    border-top: 1px solid #becfe6;
    font-size: 14px;
    font-weight: bold;
    height: 43px;
    padding-bottom: 7px;
    padding-left: 10px;
    padding-top: 3px;
  
}
.dlg_name_delete{
    background-color: #fff;
    background-repeat: repeat-x;
    border-radius: 1px;
    border-top: 1px solid #becfe6;
    font-size: 14px;
    font-weight: bold;
    height: 43px;
    padding-bottom: 7px;
    padding-left: 10px;
    padding-top: 10px;
    color: red;
  
}

.dlg_name_btn{
    background-color: #fff;
    background-repeat: repeat-x;
    border-radius: 1px;
    border: 1px solid #becfe6;
    font-size: 14px;
    font-weight: bold;
    height: 50px;
    padding-bottom: 20px;
    padding-left: 36px;
    padding-top: 7px;
  
}
.dlg_name_11{ 
    float: left;
    padding-top: 7px;
    width: 37%;
  
}
.dlg_name_12{ 
  width:60%;
  text-align:left;
  
   float: left;
}
.dlg_create{
   width: 60%;
   padding-left:126px;
   float: left;
}
.dlg_cancel{
   width: 30%;
   float: left;
}
.dlg_delete{
   width: 40%; 
   float: left;
}
.dlg_file_upload_cancel{
   width: 50%;
   float: left; 
}

.close{
  opacity: 1.00!important;
}
.text_align_left{
text-align: left;
}


.wrapper {
    width: 630px;
    margin: auto;
    background: #eee;
    border: 1px solid black;
    padding: 20px 30px;
}

.scroll {
  font-size: 30px;
  font-size: bold;  
  color: red;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;    
}
.scroll:hover {
  color: gray;
  cursor: pointer;  
}
.left-scroll { 
    position: relative;
    z-index: 10;
    left: -16px;
    top: 60px;
}    
.right-scroll { 
    position: relative;
    z-index: 10;
    left: 18px;
    top: 60px;
    float: right;
   
}    


 .glyphicon-refresh::before {
    content: "";
}
/* .glyphicon-calendar:before {
             content: "";
} */
 
.glyphicon-calendar:before {
  content: "";
}


#content_delete{
/* width: 200px;
height: 200px; */
}
.hidden{
display: none;
}
.clear{
		clear: both;
		
	}
.metadata ,.position {
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 4px;
    clear: both;
    margin: 10px;
    padding: 10px;
}
.header-table-column {
    color: BLUE;
    font-size: 12px;
    font-weight: bold;
  /*   min-width: 161px !important; */
    text-align: center;
}


.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    border-right: 1px solid #ddd;
    border-top: 1px solid #ddd;
    line-height: 1.42857;
    padding: 1px!important;
    vertical-align: top;
}
.no_margin_right{
margin-right: 0px!important;
}		
.metadata_leve1 {
  border:2px solid #a1a1a1;
padding:10px 40px; 
background:#dddddd;
width:300px;
border-radius:25px;
}
.col-lg-1 ,.col-lg-2{
   padding-top: 5px;

}
fieldset {
    border: 1px solid silver!important;
    margin: 0 2px!important;
    padding: 0.35em 0.625em 0.75em!important;
    margin-left: 10px!important;
}

.header_temple{
	font-size: 18px;
	color: teal;
	font-weight: bold;
	padding-bottom: 40px;
}

.header_temple_1{
	height: 30px;
	padding-top: 4px;
	text-align: center;
}

.row {
    font-size: 12px!important; 
    font-weight: bold;
    margin-left: 0;
    margin-right: 0;
    margin-bottom: 10px;
}
p{
margin: 0px!important;
}

 .isloading-wrapper.isloading-right {
    margin-left: 10px;
}
.isloading-overlay {
    position: relative;
    text-align: center;
   
   
  
}
  .isloading-wrapper {
        background: #FFFFFF;
        .border-radius(7px);
        display: inline-block;
        margin: 0 auto;
        padding: 10px 20px;
        top: 10%;
        z-index:9000;
         background-image: url("<%=request.getContextPath()%>/resources/img/loading.gif")!important;
   	background-repeat: no-repeat!important;
   	background-attachment: fixed;
    background-position: center; 
    border-radius:7px;
    width: 200px;
    height: 200px; 
  
      
    }
  /*   .readwarning{
    color: red!important;
    background-color: powderblue!important;
    } */
  
 
 .titile_name{
   float: left;
   width: 150px;
   padding-left: 50px;
   font-weight: bold;
   font-size: 13px;
   padding-top: 10px;
   
 }
  .input_name{
   float: left;
   width: 405px;
  
 }
 .UserCumulus{
    float: left !important;
    margin-right: 36px;   
    font-weight: bold;
    font-size: 14px;
    
 }
 
 
 

.split-pane {
	position: relative;
	height: 100%;
	width: 100%;
/* 	overflow: hidden; */
}

.split-pane.fixed-top > .split-pane-component,
.split-pane.fixed-bottom > .split-pane-component,
.split-pane.horizontal-percent > .split-pane-component {
	position: absolute;
	left: 0;
	width: 100%;
	overflow: auto;
	top: auto;
	bottom: 0;
	z-index: 1;
}

.split-pane.fixed-top > .split-pane-component:first-child,
.split-pane.fixed-bottom > .split-pane-component:first-child,
.split-pane.horizontal-percent > .split-pane-component:first-child {
	top: 0;
	bottom: auto;
}

.split-pane.fixed-top > .split-pane-divider,
.split-pane.fixed-bottom > .split-pane-divider,
.split-pane.horizontal-percent > .split-pane-divider {
	position: absolute;
	width: 100%;
	left: 0;
	cursor: ns-resize;
	cursor: n-resize/9;
	z-index: 2;
}

.split-pane.fixed-top > .split-pane-divider > .split-pane-divider-inner,
.split-pane.fixed-bottom > .split-pane-divider > .split-pane-divider-inner,
.split-pane.horizontal-percent > .split-pane-divider > .split-pane-divider-inner {
	position: absolute;
	top: -5px;
	left: 0;
	box-sizing: content-box;
	width: 100%;
	height: 100%;
	padding: 5px 0;
}

.split-pane.fixed-left > .split-pane-component,
.split-pane.fixed-right > .split-pane-component,
.split-pane.vertical-percent > .split-pane-component {
	position: absolute;
	top: 0;
	height: 100%;
	overflow: auto;
	left: auto;
	right: 0;
	z-index: 1;
}

.split-pane.fixed-left > .split-pane-component:first-child,
.split-pane.fixed-right > .split-pane-component:first-child,
.split-pane.vertical-percent > .split-pane-component:first-child {
	left: 0;
	right: auto;
}

.split-pane.fixed-left > .split-pane-divider,
.split-pane.fixed-right > .split-pane-divider,
.split-pane.vertical-percent > .split-pane-divider {
	position: absolute;
	height: 100%;
	top: 0;
	cursor: ew-resize;
	cursor: w-resize/9;
	z-index: 2;
}

.split-pane.fixed-left > .split-pane-divider > .split-pane-divider-inner,
.split-pane.fixed-right > .split-pane-divider > .split-pane-divider-inner,
.split-pane.vertical-percent > .split-pane-divider > .split-pane-divider-inner {
	position: absolute;
	top: 0;
	left: -5px;
	box-sizing: content-box;
	width: 100%;
	height: 100%;
	padding: 0 5px;
}

.split-pane-resize-shim {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 10000;
	display: none;
}

.split-pane.fixed-left > .split-pane-resize-shim,
.split-pane.fixed-right > .split-pane-resize-shim,
.split-pane.vertical-percent > .split-pane-resize-shim {
	cursor: ew-resize;
	cursor: w-resize/9;
}

.split-pane.fixed-top > .split-pane-resize-shim,
.split-pane.fixed-bottom > .split-pane-resize-shim,
.split-pane.horizontal-percent > .split-pane-resize-shim {
	cursor: ns-resize;
	cursor: n-resize/9;
}

/*end of split-pane css*/

.pretty-split-pane-frame {
	box-sizing: border-box;
	width: 100%;
	height: 100%;
	
	
}
.split-pane-divider-inner{
 background: silver; 
}
.pretty-split-pane-frame .split-pane > .split-pane-divider.dragged.touch {
	background: blue;
	opacity: 0.25;
}

.pretty-split-pane-component-inner {
	box-sizing: border-box;
	border: 1px solid gray;
	background: white;
	width: 100%;
	height: 100%;
	padding: 0 1em;
	overflow: auto;
}



			#top-component-1 {
				bottom: 20em;
				margin-bottom: 5px;
			}

			#horizontal-divider-1 {
				bottom: 20em;
				height: 5px;
			}

			#bottom-component-1 {
				height: 20em;
			}


			#top-component-2 {
				bottom: 10em;
				margin-bottom: 5px;
			}

			#horizontal-divider-2 {
				bottom: 10em;
				height: 5px;
			}

			#bottom-component-2 {
				height: 10em;
			}

			#left-component {
				width: 26em;
			}

			#vertical-divider {
				left: 26em;
				width: 5px;
			}

			#right-component {
				left: 26em;
				margin-left: 5px;
			}
			


.pretty-split-pane-frame {
  height: 95%;
}

.toolbar {
  height: 5%;
  background-color: #C3C3C3;
}

.table-th {
    background-color: #5cb85c !important;
    border-color: #4cae4c !important;
    color: #fff !important;
    font-size: 15px;
}
.code {
  white-space: nowrap;
}

.table-head {
  font-weight: 800;
}

table {
  min-width: 600px;
}

.diagram-section {
   
    background-size: contain;
    background-repeat: no-repeat;
    background-color: white;
  background-position: center; 
  width: 100%;
  height: 100%;
}

.pretty-split-pane-component-inner {
  position: relative;
}

.section-content {
  padding-top: 25px;  
}

.title-section {
  background-color: #42555F;
    position: absolute;
    width: 100%;
    left: 0px;
    line-height: 0;
    padding-left: 13px;
  color: white;
}

.col-lg-1,.col-lg-2,.col-lg-3,.col-lg-4,.col-lg-5,.col-lg-6{
   margin: 0px;
}

.btn-glyphicon {
    background: #ffffff none repeat scroll 0 0;
    margin-right: 4px;
    padding: 8px;
}
.btn_add_remove{
  width: 50px;
  padding-left: 5px;
  float: left;
}
.well {
  margin: 0px!important;
  float: right;
}

</style>
</head>
<body>
 <%
  String data ="['August Beck GmbH & Co. KG|0000010367|DE72600501010004763594|EUR','Bedrunka & Hirth GmbH|0000014053|DE90694500650240012576|EUR','Bilz Werkzeugfabrik GmbH & Co. KG|0000010078|DE69600501017456500267|EUR','C.+ E. Fein GmbH|0000010399|DE51600700700110213600|EUR','E D E Einkaufsbüro Deutscher|0000015179|DE68300306008003800300|EUR','Fechtel Transportgeräte GmbH|0000013582|DE90480620510214900100|EUR','Gühring KG|0000010431|DE09653700750010412500|EUR','Jörn Detjens GmbH|0000017273|DE24200505501352122855|EUR','KLW Karl Lutz GmbH & Co. KG|0000010655|DE84600501010008800130|EUR','LOKOMA Lorenz Kollmann GmbH|0000010600|DE52722515200000554405|EUR','Owt Osnabrücker Werkzeugtechnik Gmb|0000017826|DE57265501050000280271|EUR','Rhodius GmbH & Co. KG|0000013325|DE80570200860004768507|EUR','Robert Bosch Power Tools GmbH|0000012810|DE24600501010002250315|EUR','Scheuerlein-Motorentechnik|0000020074|DE49760693720007217714|EUR','Stanley Black & Decker|0000018939|DE10500400000123900300|EUR','Ultra-Präzision Messzeuge GmbH|0000011948|DE41795500000000300848|EUR','Walter Deutschland GmbH|0000011900|DE21500700100341203800|EUR','Wera Werkzeuge GmbH|0000012128|DE97330700900093182400|EUR']";
  String data_postion ="['Abroad|Abroad|surcharge','Administration|Administration|surcharge','bulky goods|bulky goods|surcharge','Cash discount|Cash discount|surcharge','Cash on delivery|C.O.D. cash on delivery service|surcharge','Customization|Customization|surcharge','Customs|Customs|surcharge','Deposit|Deposit|surcharge','Express|Express|surcharge','Extra isle-charge|Surcharge for islands.|surcharge','Fracht|Freight|surcharge','Frachtkosten|Freight|surcharge','Frachtspesen|Freight|surcharge','Freight|Freight|surcharge','Handling|Handling|surcharge','Insurance|Insurance|surcharge','Minimum quantitiy Surcharge|Minimum quantitiy Surcharge|surcharge','Packing|Packing|surcharge','Partial Quantity|Partial Quantity|surcharge','Period-bonus|Period-bonus (e.g. annual bonus)|allowance','Postage|Postage|surcharge','Project-bonus|Project-bonus|allowance','Rebate|Rebate|allowance','Recycling|Recycling|surcharge','Surcharge|Surcharge|surcharge','Surcharge for material|Surcharge for material|surcharge','Verpackung|Packing|surcharge']";
 %>
  
   <div id="header">  
		<div class="navbar nav_title" style="border: 0;">
			<a class="site_title" href="homeAction">
				<img width="27%" height="70%" alt="ĐỒNG XANH" src="./images/banner_dongxanh.png" style="padding-top: 10px">
			</a>
		</div>
	</div>
	<div class="container"  id="center" style="padding-left: 0px;">
	
	
<!--   <div class="tab-content"> -->
<div class="pretty-split-pane-frame">
  <div class="split-pane fixed-left" id="split-pane-1">
    <div class="split-pane-component diagram-section" id="left-component">     
    <div class="pretty-split-pane-component-inner">
						<div id="wrapper" style="width : 100%; height : 100%; float: left">
							<div class="row">
								<div class="col-lg-12">
									<div class="panel panel-default">
										<div id="viewer" class="viewer row content"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
      
      
    </div>

    <!--verical divider-->
    <div class="split-pane-divider" id="vertical-divider"></div>
    <!--verical divider-->

    <div class="split-pane-component" id="right-component">
      <div class="split-pane fixed-bottom diagram-section" id="split-pane-2">

    	<form name = "form-field" id = "form-text-field" action = "saveform" method = "post" onsubmit = "return confirmwhensubmit()">
    	                <div class="well"><p align="center">				            
				                 <button type="button" class="btn btn-success" onclick="searchDetail();">Xóa toa/bảng kê</button>			  
				            	 <button type="button" class="btn btn-success" onclick="searchDetail();">Lưu toa/bảng kê</button>			  
                      	</div>
						<div id="wrapper" style="width : 100%; height : 100%; float: right">
							<!-- <div class="panel-heading" style="color: blue;font-weight: bold;">META DATA </div> -->
									<div class="metadata" style="z-index: 1">									    
									        <div class="row " >
	                                       		  <div class="col-lg-2" ><span>Loại bảng kê  </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">	
	                                       		   <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search" style="width: 100% ; height: 27px">
										                 <option value="">All</option>	
										                 <option value="">All</option>	
										                 <option value="">All</option>	
										                 <option value="">All</option>	
										                 <option value="">All</option>				                  
										           </select>
	                                       		  </div>	 
	                                       		  
	                                       		  <div class="col-lg-2" ><span>Tên bảng kê  </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "tenbangke" name="tenbangke" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                        		  
	                                        </div>
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-2" ><span>Mã KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "maKH" name="maKH" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>Tên KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "tenKH" name="tenKH" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                       		  
	                                        </div>
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-2" ><span>KH Cấp 1 </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "KHcap1" name="KHcap1" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>NVTT </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "NVTT" name="NVTT" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                                 		  
	                                        </div>
										     <div class="clear"></div>	
										      <div class="row " >
	                                       		  <div class="col-lg-2 no_margin_right" ><span>Ngày nhận toa </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "ngaynhantoa" name="ngaynhantoa" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2 no_margin_right" ><span>Ngày nhận hàng </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "ngaynhanhang" name="ngaynhanhang" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                          		  
	                                        </div>
	                                        
	                                        <div class="clear"></div>	
										      <div class="row " >
	                                       		  <div class="col-lg-2 no_margin_right" ><span>Số ngày gởi trể</span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "kreditor_name" name="kreditor_name" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>Ghi chú </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "kreditor_name" name="kreditor_name" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                          		  
	                                        </div>
										     <div class="clear"></div>
										     
	                              </div> 	                                      
				            <!-- /.row -->
				            <div class="clear"></div>
				            <div class="position" style="z-index: 1">	
						    <div class="row">
							      <div class="position_table table-wrapper-scroll-y">							       
							        <table  id="table_position" class="table table-fixed table-bordered table-inverse "  id="datatable-debitor">
							          <thead>
							            <tr>										            
							             <th class = "header-table-column  table-th">STT</th>
							             <th class = "header-table-column  table-th">Mã sản phẩm</th>
							             <th class = "header-table-column  table-th">Tên sản phẩm</th>
							             <th class = "header-table-column  table-th">Số lượng</th>
							             <th class = "header-table-column  table-th">Số thùng</th>
							             <th class = "header-table-column  table-th">Đơn giá</th>
							             <th class = "header-table-column  table-th">Thành tiền</th>
							            
							             <th class = "header-table-column  table-th" style="width: 80px"></th>
							            </tr>
							          </thead>
							          <tbody>
							          <% for(int i = 1; i < 10; i++) {%> 
		                                <tr class="odd gradeX" id = "<%=i %>" >
		                                    <td id = "stt_<%=i %>">	<%=i %></td>
											
											<td>
												<input value = "" id = "masanpham_<%=i %>"    name="masanpham_<%=i %>"  class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "tensanpham_<%=i %>"   name="tensanpham_<%=i %>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "soluong_<%=i %>"      name="soluong_<%=i %>"    class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "sothung_<%=i %>"      name="sothung_<%=i %>"     class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											
											<td>
												<input value = "" id = "dongia_<%=i %>"        name="dongia_<%=i %>"     class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											
											<td>
												<input value = "" id = "thanhtien_<%=i %>"      name="thanhtien_<%=i %>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
											   <div style="padding-top: 4px; padding-left: 2px; padding-right: 2px">
											        <button type="button" class="btn btn-success btn-sm" onclick="addRow(<%=i %>)"><span class="glyphicon glyphicon-plus"></span></button>
                                                    <button type="button" class="btn btn-danger btn-sm"  onclick="moveRow(<%=i %>)"><span class="glyphicon glyphicon-remove"></span></button>											
											   </div>
												
											</td>
											
										</tr>
										<% } %>			 		
							          </tbody>
							        </table>
							      </div>
							  </div>   
				          
                      		
						</div>
						</div>
						</form>
						
						

    </div>
  </div>
  
  
  
	
	  </div>
	</div>
	</div>
<!-- 	</div> -->

  <div id="footer">
  	   <div style="margin:10px auto;">
			@Copyright by <a>Thien Toan</a>. | <span class="lead"> 
				<img src="./images/logo_dongxanh.png" alt="ĐỒNG XANH" height="25" width="25"> ĐỒNG XANH
				</span>
           
		</div>
	</div>	
<script type="text/javascript" src="js/jquery.mousewheel.min.js" ></script>
<script type="text/javascript" src="js/jquery.iviewer.js" ></script>  
<script type="text/javascript">
function addRow(id){
	var stt =1;
	var index =1;
	
	 $('#table_position > tbody  > tr').each(function() {
		 stt++;
		 if(this.getAttribute("id")==id){			
			index = stt;			
		 }
		 
	 });
	
	  var table = document.getElementById("table_position");
	  var row = table.insertRow(index);
	  var stt        = row.insertCell(0);
	  var masp      = row.insertCell(1);
	  var tensp     = row.insertCell(2);
	  var soluong   = row.insertCell(3);
	  var sothung   = row.insertCell(4);
	  var dongia    = row.insertCell(5);
	  var thanhtien = row.insertCell(6);
	  var addremove = row.insertCell(7);
	  var time_id   = new Date().getTime();
	  
	  row.id =time_id;
	  stt.id             ="stt_"+time_id;
	  stt.innerHTML       = "";
	  masp.innerHTML      = "<input value = '' id = 'masanpham_"+time_id+"'     name='masanpham_"+time_id+"'  class='custom-input-debitor form-control' ondblclick = 'getIdTag(this)' tabindex=''/>";
	  tensp.innerHTML     = "<input value = '' id = 'tensanpham_"+time_id+"'    name='tensanpham_"+time_id+"' class='custom-input-debitor form-control' ondblclick = 'getIdTag(this)' tabindex=''/>";
	  soluong.innerHTML   = "<input value = '' id = 'soluong_"+time_id+"'       name='soluong_"+time_id+"'    class='custom-input-debitor form-control' ondblclick = 'getIdTag(this)' tabindex=''/>";
	  sothung.innerHTML   = "<input value = '' id = 'sothung_"+time_id+"'       name='sothung_"+time_id+"'    class='custom-input-debitor form-control' ondblclick = 'getIdTag(this)' tabindex=''/>";
	  dongia.innerHTML    = "<input value = '' id = 'dongia_"+time_id+"'        name='dongia_"+time_id+"'     class='custom-input-debitor form-control' ondblclick = 'getIdTag(this)' tabindex=''/>";
	  thanhtien.innerHTML = "<input value = '' id = 'thanhtien_"+time_id+"'     name='thanhtien_"+time_id+"'  class='custom-input-debitor form-control' ondblclick = 'getIdTag(this)' tabindex=''/>";
	 
	  var add_remove  = "<div style='padding-top: 4px; padding-left: 2px; padding-right: 2px'>  ";   
	      add_remove  +="<button type='button' class='btn btn-success btn-sm' onclick='addRow("+time_id+")'><span class='glyphicon glyphicon-plus'></span></button> ";
	      add_remove  +="<button type='button' class='btn btn-danger btn-sm'  onclick='moveRow("+time_id+")'><span class='glyphicon glyphicon-remove'></span></button>";
	      add_remove  +="</div>";
	      
	  addremove.innerHTML =add_remove;
	  
	    var nebenkosten = document.getElementById("masanpham_"+time_id);
		var nebenkosten_betrag = document.getElementById("tensanpham_"+time_id);
		var nebenkosten_type = document.getElementById("soluong_"+time_id);
		
		new LookupKreditor(nebenkosten, nebenkosten_betrag, nebenkosten_type, null, 4, {
			list : <%=data_postion%>
		});
		
	  stt =0;
	  $('#table_position > tbody  > tr').each(function() {
			  stt++;	
			  document.getElementById("stt_"+this.getAttribute("id")).innerHTML  = stt;        
			 
			 
		 });
	
}

function moveRow(id){
	var result = confirm("bạn có chắt chắn chắn xóa dòng này?");
	if (result) {
		var stt =1;
		var index =-1;	
		 $('#table_position > tbody  > tr').each(function() {
			 stt;
			 if(this.getAttribute("id")==id){			
				index = stt;			
			 }		 
		 });	
		  var table = document.getElementById("table_position");
		  table.deleteRow(index);	 
		  stt =0;
		  $('#table_position > tbody  > tr').each(function() {
				  stt++;	
				  document.getElementById("stt_"+this.getAttribute("id")).innerHTML  = stt;
			 });
		
	}
	
}
/*!

Split Pane v0.5.0

Copyright (c) 2014 Simon Hagström

Released under the MIT license
https://raw.github.com/shagstrom/split-pane/master/LICENSE

*/
(function($) {

  $.fn.splitPane = function() {
    var $splitPanes = this;
    $splitPanes.each(setMinHeightAndMinWidth);
    $splitPanes.append('<div class="split-pane-resize-shim">');
    var eventType = ('ontouchstart' in document) ? 'touchstart' : 'mousedown';
    $splitPanes.children('.split-pane-divider').html('<div class="split-pane-divider-inner"></div>');
    $splitPanes.children('.split-pane-divider').bind(eventType, mousedownHandler);
    setTimeout(function() {
      // Doing this later because of an issue with Chrome (v23.0.1271.64) returning split-pane width = 0
      // and triggering multiple resize events when page is being opened from an <a target="_blank"> .
      $splitPanes.each(function() {
        $(this).bind('_splitpaneparentresize', createParentresizeHandler($(this)));
      });
      $(window).trigger('resize');
    }, 100);
  };

  var SPLITPANERESIZE_HANDLER = '_splitpaneparentresizeHandler';

  /**
   * A special event that will "capture" a resize event from the parent split-pane or window.
   * The event will NOT propagate to grandchildren.
   */
  jQuery.event.special._splitpaneparentresize = {
    setup: function(data, namespaces) {
      var element = this,
        parent = $(this).parent().closest('.split-pane')[0] || window;
      $(this).data(SPLITPANERESIZE_HANDLER, function(event) {
        var target = event.target === document ? window : event.target;
        if (target === parent) {
          event.type = "_splitpaneparentresize";
          jQuery.event.dispatch.apply(element, arguments);
        } else {
          event.stopPropagation();
        }
      });
      $(parent).bind('resize', $(this).data(SPLITPANERESIZE_HANDLER));
    },
    teardown: function(namespaces) {
      var parent = $(this).parent().closest('.split-pane')[0] || window;
      $(parent).unbind('resize', $(this).data(SPLITPANERESIZE_HANDLER));
      $(this).removeData(SPLITPANERESIZE_HANDLER);
    }
  };

  function setMinHeightAndMinWidth() {
    var $splitPane = $(this),
      $firstComponent = $splitPane.children('.split-pane-component:first'),
      $divider = $splitPane.children('.split-pane-divider'),
      $lastComponent = $splitPane.children('.split-pane-component:last');
    if ($splitPane.is('.fixed-top, .fixed-bottom, .horizontal-percent')) {
      $splitPane.css('min-height', (minHeight($firstComponent) + minHeight($lastComponent) + $divider.height()) + 'px');
    } else {
      $splitPane.css('min-width', (minWidth($firstComponent) + minWidth($lastComponent) + $divider.width()) + 'px');
    }
  }

  function mousedownHandler(event) {
    event.preventDefault();
    var isTouchEvent = event.type.match(/^touch/),
      moveEvent = isTouchEvent ? 'touchmove' : 'mousemove',
      endEvent = isTouchEvent ? 'touchend' : 'mouseup',
      $divider = $(this),
      $splitPane = $divider.parent(),
      $resizeShim = $divider.siblings('.split-pane-resize-shim');
    $resizeShim.show();
    $divider.addClass('dragged');
    if (isTouchEvent) {
      $divider.addClass('touch');
    }
    var moveEventHandler = createMousemove($splitPane, pageXof(event), pageYof(event));
    $(document).on(moveEvent, moveEventHandler);
    $(document).one(endEvent, function(event) {
      $(document).unbind(moveEvent, moveEventHandler);
      $divider.removeClass('dragged touch');
      $resizeShim.hide();
    });
  }

  function createParentresizeHandler($splitPane) {
    var splitPane = $splitPane[0],
      firstComponent = $splitPane.children('.split-pane-component:first')[0],
      divider = $splitPane.children('.split-pane-divider')[0],
      lastComponent = $splitPane.children('.split-pane-component:last')[0];
    if ($splitPane.is('.fixed-top')) {
      var lastComponentMinHeight = minHeight(lastComponent);
      return function(event) {
        var maxfirstComponentHeight = splitPane.offsetHeight - lastComponentMinHeight - divider.offsetHeight;
        if (firstComponent.offsetHeight > maxfirstComponentHeight) {
          setTop(firstComponent, divider, lastComponent, maxfirstComponentHeight + 'px');
        }
        $splitPane.resize();
      };
    } else if ($splitPane.is('.fixed-bottom')) {
      var firstComponentMinHeight = minHeight(firstComponent);
      return function(event) {
        var maxLastComponentHeight = splitPane.offsetHeight - firstComponentMinHeight - divider.offsetHeight;
        if (lastComponent.offsetHeight > maxLastComponentHeight) {
          setBottom(firstComponent, divider, lastComponent, maxLastComponentHeight + 'px')
        }
        $splitPane.resize();
      };
    } else if ($splitPane.is('.horizontal-percent')) {
      var lastComponentMinHeight = minHeight(lastComponent),
        firstComponentMinHeight = minHeight(firstComponent);
      return function(event) {
        var maxLastComponentHeight = splitPane.offsetHeight - firstComponentMinHeight - divider.offsetHeight;
        if (lastComponent.offsetHeight > maxLastComponentHeight) {
          setBottom(firstComponent, divider, lastComponent, (maxLastComponentHeight / splitPane.offsetHeight * 100) + '%');
        } else {
          if (splitPane.offsetHeight - firstComponent.offsetHeight - divider.offsetHeight < lastComponentMinHeight) {
            setBottom(firstComponent, divider, lastComponent, (lastComponentMinHeight / splitPane.offsetHeight * 100) + '%');
          }
        }
        $splitPane.resize();
      };
    } else if ($splitPane.is('.fixed-left')) {
      var lastComponentMinWidth = minWidth(lastComponent);
      return function(event) {
        var maxFirstComponentWidth = splitPane.offsetWidth - lastComponentMinWidth - divider.offsetWidth;
        if (firstComponent.offsetWidth > maxFirstComponentWidth) {
          setLeft(firstComponent, divider, lastComponent, maxFirstComponentWidth + 'px');
        }
        $splitPane.resize();
      };
    } else if ($splitPane.is('.fixed-right')) {
      var firstComponentMinWidth = minWidth(firstComponent);
      return function(event) {
        var maxLastComponentWidth = splitPane.offsetWidth - firstComponentMinWidth - divider.offsetWidth;
        if (lastComponent.offsetWidth > maxLastComponentWidth) {
          setRight(firstComponent, divider, lastComponent, maxLastComponentWidth + 'px');
        }
        $splitPane.resize();
      };
    } else if ($splitPane.is('.vertical-percent')) {
      var lastComponentMinWidth = minWidth(lastComponent),
        firstComponentMinWidth = minWidth(firstComponent);
      return function(event) {
        var maxLastComponentWidth = splitPane.offsetWidth - firstComponentMinWidth - divider.offsetWidth;
        if (lastComponent.offsetWidth > maxLastComponentWidth) {
          setRight(firstComponent, divider, lastComponent, (maxLastComponentWidth / splitPane.offsetWidth * 100) + '%');
        } else {
          if (splitPane.offsetWidth - firstComponent.offsetWidth - divider.offsetWidth < lastComponentMinWidth) {
            setRight(firstComponent, divider, lastComponent, (lastComponentMinWidth / splitPane.offsetWidth * 100) + '%');
          }
        }
        $splitPane.resize();
      };
    }
  }

  function createMousemove($splitPane, pageX, pageY) {
    var splitPane = $splitPane[0],
      firstComponent = $splitPane.children('.split-pane-component:first')[0],
      divider = $splitPane.children('.split-pane-divider')[0],
      lastComponent = $splitPane.children('.split-pane-component:last')[0];
    if ($splitPane.is('.fixed-top')) {
      var firstComponentMinHeight = minHeight(firstComponent),
        maxFirstComponentHeight = splitPane.offsetHeight - minHeight(lastComponent) - divider.offsetHeight,
        topOffset = divider.offsetTop - pageY;
      return function(event) {
        event.preventDefault();
        var top = Math.min(Math.max(firstComponentMinHeight, topOffset + pageYof(event)), maxFirstComponentHeight);
        setTop(firstComponent, divider, lastComponent, top + 'px');
        $splitPane.resize();
      };
    } else if ($splitPane.is('.fixed-bottom')) {
      var lastComponentMinHeight = minHeight(lastComponent),
        maxLastComponentHeight = splitPane.offsetHeight - minHeight(firstComponent) - divider.offsetHeight,
        bottomOffset = lastComponent.offsetHeight + pageY;
      return function(event) {
        event.preventDefault();
        var bottom = Math.min(Math.max(lastComponentMinHeight, bottomOffset - pageYof(event)), maxLastComponentHeight);
        setBottom(firstComponent, divider, lastComponent, bottom + 'px');
        $splitPane.resize();
      };
    } else if ($splitPane.is('.horizontal-percent')) {
      var splitPaneHeight = splitPane.offsetHeight,
        lastComponentMinHeight = minHeight(lastComponent),
        maxLastComponentHeight = splitPaneHeight - minHeight(firstComponent) - divider.offsetHeight,
        bottomOffset = lastComponent.offsetHeight + pageY;
      return function(event) {
        event.preventDefault();
        var bottom = Math.min(Math.max(lastComponentMinHeight, bottomOffset - pageYof(event)), maxLastComponentHeight);
        setBottom(firstComponent, divider, lastComponent, (bottom / splitPaneHeight * 100) + '%');
        $splitPane.resize();
      };
    } else if ($splitPane.is('.fixed-left')) {
      var firstComponentMinWidth = minWidth(firstComponent),
        maxFirstComponentWidth = splitPane.offsetWidth - minWidth(lastComponent) - divider.offsetWidth,
        leftOffset = divider.offsetLeft - pageX;
      return function(event) {
        event.preventDefault();
        var left = Math.min(Math.max(firstComponentMinWidth, leftOffset + pageXof(event)), maxFirstComponentWidth);
        setLeft(firstComponent, divider, lastComponent, left + 'px')
        $splitPane.resize();
      };
    } else if ($splitPane.is('.fixed-right')) {
      var lastComponentMinWidth = minWidth(lastComponent),
        maxLastComponentWidth = splitPane.offsetWidth - minWidth(firstComponent) - divider.offsetWidth,
        rightOffset = lastComponent.offsetWidth + pageX;
      return function(event) {
        event.preventDefault();
        var right = Math.min(Math.max(lastComponentMinWidth, rightOffset - pageXof(event)), maxLastComponentWidth);
        setRight(firstComponent, divider, lastComponent, right + 'px');
        $splitPane.resize();
      };
    } else if ($splitPane.is('.vertical-percent')) {
      var splitPaneWidth = splitPane.offsetWidth,
        lastComponentMinWidth = minWidth(lastComponent),
        maxLastComponentWidth = splitPaneWidth - minWidth(firstComponent) - divider.offsetWidth,
        rightOffset = lastComponent.offsetWidth + pageX;
      return function(event) {
        event.preventDefault();
        var right = Math.min(Math.max(lastComponentMinWidth, rightOffset - pageXof(event)), maxLastComponentWidth);
        setRight(firstComponent, divider, lastComponent, (right / splitPaneWidth * 100) + '%');
        $splitPane.resize();
      };
    }
  }

  function pageXof(event) {
    return event.pageX || event.originalEvent.pageX;
  }

  function pageYof(event) {
    return event.pageY || event.originalEvent.pageY;
  }

  function minHeight(element) {
    return parseInt($(element).css('min-height')) || 0;
  }

  function minWidth(element) {
    return parseInt($(element).css('min-width')) || 0;
  }

  function setTop(firstComponent, divider, lastComponent, top) {
    firstComponent.style.height = top;
    divider.style.top = top;
    lastComponent.style.top = top;
  }

  function setBottom(firstComponent, divider, lastComponent, bottom) {
    firstComponent.style.bottom = bottom;
    divider.style.bottom = bottom;
    lastComponent.style.height = bottom;
  }

  function setLeft(firstComponent, divider, lastComponent, left) {
    firstComponent.style.width = left;
    divider.style.left = left;
    lastComponent.style.left = left;
  }

  function setRight(firstComponent, divider, lastComponent, right) {
    firstComponent.style.right = right;
    divider.style.right = right;
    lastComponent.style.width = right;
  }

})(jQuery);

// end of plugin js

$(function() {
  $('div.split-pane').splitPane();
  $('.diagram').click(function() {

    $('#left-component').css('width', '0px');
    $('#right-component').css('left', '0px');
    $('#vertical-divider').css('left', '0px');

    $('#top-component-2').css('bottom', '0px');
    $('#bottom-component-2').css('height', '0px');
    $('#horizontal-divider-2').css('bottom', '0px');
  });

  $('.props').click(function() {

    var winWidth = $('.split-pane').width();
    var winHeight = $('.split-pane').height();

    $('#left-component').css('width', '0px');
    $('#right-component').css('left', '0px');
    $('#vertical-divider').css('left', '0px');

    $('#top-component-2').css('bottom', winHeight - 5 + 'px');
    $('#bottom-component-2').css('height', winHeight - 5 + 'px');
    $('#horizontal-divider-2').css('bottom', winHeight - 5 + 'px');
  });

  $('.code').click(function() {

    var winWidth = $('.split-pane').width();
    var winHeight = $('.split-pane').height();

    $('#left-component').css('width', winWidth - 5 + 'px');
    $('#right-component').css('left', winWidth - 5 + 'px');
    $('#vertical-divider').css('left', winWidth - 5 + 'px');

    $('#top-component-1').css('bottom', '0px');
    $('#bottom-component-1').css('height', '0px');
    $('#horizontal-divider-1').css('bottom', '0px');
  });

  $('.console').click(function() {

    var winWidth = $('.split-pane').width();
    var winHeight = $('.split-pane').height();

    $('#left-component').css('width', winWidth - 5 + 'px');
    $('#right-component').css('left', winWidth - 5 + 'px');
    $('#vertical-divider').css('left', winWidth - 5 + 'px');

    $('#top-component-1').css('bottom', winHeight - 5 + 'px');
    $('#bottom-component-1').css('height', winHeight - 5 + 'px');
    $('#horizontal-divider-1').css('bottom', winHeight - 5 + 'px');
  });
  
    $('.reset').click(function() {

    var winWidth = $('.split-pane').width();
    var winHeight = $('.split-pane').height();

    $('#left-component').css('width', winWidth / 2 + 'px');
    $('#right-component').css('left', winWidth / 2  + 'px');
    $('#vertical-divider').css('left', winWidth / 2 + 'px');

    $('#top-component-1').css('bottom', winHeight / 2 + 'px');
    $('#bottom-component-1').css('height', winHeight / 2 + 'px');
    $('#horizontal-divider-1').css('bottom', winHeight / 2 + 'px');
      
    $('#top-component-2').css('bottom', winHeight / 3 + 'px');
    $('#bottom-component-2').css('height', winHeight / 3 + 'px');
    $('#horizontal-divider-2').css('bottom', winHeight / 3 + 'px'); 
  });

});


$(document).ready(function() {
	 var winWidth = $('.split-pane').width();
	  var winHeight = $('.split-pane').height();

	  $('#left-component').css('width', winWidth / 2 + 'px');
	  $('#right-component').css('left', winWidth / 2  + 'px');
	  $('#vertical-divider').css('left', winWidth / 2 + 'px');

	  $('#top-component-1').css('bottom', winHeight / 2 + 'px');
	  $('#bottom-component-1').css('height', winHeight / 2 + 'px');
	  $('#horizontal-divider-1').css('bottom', winHeight / 2 + 'px');
	    
	  $('#top-component-2').css('bottom', winHeight / 3 + 'px');
	  $('#bottom-component-2').css('height', winHeight / 3 + 'px');
	  $('#horizontal-divider-2').css('bottom', winHeight / 3 + 'px');
	
	  $('#table_position').DataTable({
		  "scrollY": "360px",
		  "scrollCollapse": true,
	   });
	
	 
	
});
function setdataCheckBox(value , id){
	if(value==1){	
		$(id).prop('checked', true);
	}else{		
		$(id).prop('checked', false);
	}
	
}
function confirmwhensubmit(){	
	var kyc_note_prepare    =  $.trim($('#kyc_note_prepare').val());	
	var kycstatus          =  $('input[name=kycstatus]:checked', '#myForm').val(); 
	
	if(kycstatus=='REJECTED'  && kyc_note_prepare==''){
		alert("Vui lòng bổ sung Rejected Reason.");
		return false;
	}
	if(kycstatus == null){
		alert("Vui lòng chọn trạng thái KYC.");
		return false;
	}
	
	return true;
}
</script> 
 <!-- VIEW IMAGE -->
    
    <script type="text/javascript">
       		
       	
       		var imagePath = "https://i-giaitri.vnecdn.net/2018/12/19/rowing2-1545228554-4160-1545228557_500x300.jpg";
            var $ = jQuery;
            $(document).ready(function(){
            
                var iv = $("#viewer").iviewer({
              	   src : imagePath,
                     update_on_resize: true,
                     zoom_animation: true,
                     mousewheel: false,
                     onMouseMove: function(ev, coords) { return true },
                     onStartDrag: function(ev, coords) { return true },
                     onDrag: function(ev, coords) { return true }
                });
                $("#in").click(function(){ iv1.iviewer('zoom_by', 1); });
                $("#out").click(function(){ iv1.iviewer('zoom_by', -1); });
                $("#fit").click(function(){ iv1.iviewer('fit'); });
                $("#orig").click(function(){ iv1.iviewer('set_zoom', 100); });
                $("#update").click(function(){ iv1.iviewer('update_container_info'); });
          });
        	
        		var kreditor_name = document.getElementById("maKH");
        		var kreditor_id   = document.getElementById("tenKH");
        		var iban          = document.getElementById("KHcap1");
        		var currency      = document.getElementById("NVTT");
        		
        		new LookupKreditor(kreditor_name, kreditor_id, iban, currency, 1, {
        			list: <%=data%>
        		});
        		
        		  $('#table_position > tbody  > tr').each(function() {
        			    var nebenkosten = document.getElementById("masanpham_"+this.getAttribute("id"));
        				var nebenkosten_betrag = document.getElementById("tensanpham_"+this.getAttribute("id"));
        				var nebenkosten_type = document.getElementById("soluong_"+this.getAttribute("id"));
        				
        				new LookupKreditor(nebenkosten, nebenkosten_betrag, nebenkosten_type, null, 4, {
        					list : <%=data_postion%>
        				});
        				
        				
        				new LookupKreditor(nebenkosten_type,nebenkosten, nebenkosten_betrag, null, 4, {
        					list : <%=data_postion%>
        				});
        				
        				new LookupKreditor(nebenkosten_betrag,nebenkosten_type,nebenkosten, null, 4, {
        					list : <%=data_postion%>
        				});
    				 
    				  
    			 });
        		
        		  $(function(){
        	   			$("#viewer").jScrollPane();
        	   		});
        	
    </script>
    
    
    
</body>
</html>
