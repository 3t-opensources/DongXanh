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
  
<link href="css/hoadon_nhap.css" type="text/css" rel="stylesheet">
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
