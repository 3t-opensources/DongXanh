<%@page import="com.home.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.home.model.InvoiceType"%>
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




<link rel="stylesheet" href="css/datepicker.min.css"/>




<script type="text/javascript" src="js/docs-js-jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>


<script src="js/jquery.dataTables.min.js"></script>
	<!-- LOOKUP DATA -->
<link href="css/awesomplete.css" type="text/css" rel="stylesheet">
<script src="js/awesomplete_new.js"></script>

 

</head>
<body>
 <%
 
 List<InvoiceType> listInvoice =  (ArrayList<InvoiceType>) request.getAttribute("listInvoiceType");
 User userSes                  =  ( User) request.getAttribute("userSes");	
  String datatest ="['August Beck GmbH & Co. KG|0000010367|DE72600501010004763594|EUR','Bedrunka & Hirth GmbH|0000014053|DE90694500650240012576|EUR','Bilz Werkzeugfabrik GmbH & Co. KG|0000010078|DE69600501017456500267|EUR','C.+ E. Fein GmbH|0000010399|DE51600700700110213600|EUR','E D E Einkaufsbüro Deutscher|0000015179|DE68300306008003800300|EUR','Fechtel Transportgeräte GmbH|0000013582|DE90480620510214900100|EUR','Gühring KG|0000010431|DE09653700750010412500|EUR','Jörn Detjens GmbH|0000017273|DE24200505501352122855|EUR','KLW Karl Lutz GmbH & Co. KG|0000010655|DE84600501010008800130|EUR','LOKOMA Lorenz Kollmann GmbH|0000010600|DE52722515200000554405|EUR','Owt Osnabrücker Werkzeugtechnik Gmb|0000017826|DE57265501050000280271|EUR','Rhodius GmbH & Co. KG|0000013325|DE80570200860004768507|EUR','Robert Bosch Power Tools GmbH|0000012810|DE24600501010002250315|EUR','Scheuerlein-Motorentechnik|0000020074|DE49760693720007217714|EUR','Stanley Black & Decker|0000018939|DE10500400000123900300|EUR','Ultra-Präzision Messzeuge GmbH|0000011948|DE41795500000000300848|EUR','Walter Deutschland GmbH|0000011900|DE21500700100341203800|EUR','Wera Werkzeuge GmbH|0000012128|DE97330700900093182400|EUR']";
 
  String data_postion ="['Abroad|Abroad|surcharge','Administration|Administration|surcharge','bulky goods|bulky goods|surcharge','Cash discount|Cash discount|surcharge','Cash on delivery|C.O.D. cash on delivery service|surcharge','Customization|Customization|surcharge','Customs|Customs|surcharge','Deposit|Deposit|surcharge','Express|Express|surcharge','Extra isle-charge|Surcharge for islands.|surcharge','Fracht|Freight|surcharge','Frachtkosten|Freight|surcharge','Frachtspesen|Freight|surcharge','Freight|Freight|surcharge','Handling|Handling|surcharge','Insurance|Insurance|surcharge','Minimum quantitiy Surcharge|Minimum quantitiy Surcharge|surcharge','Packing|Packing|surcharge','Partial Quantity|Partial Quantity|surcharge','Period-bonus|Period-bonus (e.g. annual bonus)|allowance','Postage|Postage|surcharge','Project-bonus|Project-bonus|allowance','Rebate|Rebate|allowance','Recycling|Recycling|surcharge','Surcharge|Surcharge|surcharge','Surcharge for material|Surcharge for material|surcharge','Verpackung|Packing|surcharge']";
 %>
  
   <div id="header">  
		<div class="navbar nav_title" style="border: 0;">
			<a class="site_title" href="homeAction">
				<img width="27%" height="70%" alt="ĐỒNG XANH" src="./images/banner_dongxanh.png" style="padding-top: 10px ; display: none;">
				
				<img width="27%" height="70%" alt="ĐỒNG XANH" src="https://digi-texx.vn/wp-content/uploads/2018/06/Logo_DIGI-TEXX_2015-e1530520430447.png" style="padding-top: 10px">
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
    	              <input type="hidden" id="id" name="id" value="123">
        			  <input type="hidden" id="management_id" name="management_id" value="456">
        			  <input type="hidden" id="invoice_type_id" name="invoice_type_id" value="789">
        			
        			  
        			  <input type="hidden" id="user_id" name="user_id" value="<%= userSes.getId()%>">
        			  <input type="hidden" id="user_name" name="user_name" value="<%= userSes.getUserName()%>">
        			  
        			  
    	                <div class="well"><p align="center">
    	                         <button type="button" id ="btn_nhapbangke" class="btn btn-success" onclick="getjob()">Nhập bảng kê</button>						            
				                 <button type="button" id ="btn_xoabangke"  class="btn btn-success" onclick="searchDetail();">Xóa toa/bảng kê</button>			  
				            	 <button type="button" id ="btn_Luubangke"  class="btn btn-success" onclick="saveData();">Lưu toa/bảng kê</button>			  
                      	</div>
						<div id="wrapper" style="width : 100%; height : 100%; float: right">
							<!-- <div class="panel-heading" style="color: blue;font-weight: bold;">META DATA </div> -->
									<div class="metadata" style="z-index: 1">									    
									        <div class="row " >
	                                       		  <div class="col-lg-2" ><span>Loại bảng kê  </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">	
	                                       		  
	                                       		   <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search" style="width: 100% ; height: 27px">
										              <%										             									
										              for(InvoiceType item : listInvoice){ %>
										            	<option value="<%= item.id%>"><%= item.invoiceType %></option>
										            	
										            	
										             <%   
										              }	
										             
										              %>
										              	                  
										           </select>
	                                       		  </div>	 
	                                       		  
	                                       			                                        		  
	                                        </div>
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-2" ><span>Mã KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">	
	                                       		          <input type="hidden" id="customer_id_hidden" name="customer_id_hidden" value="789">		                                       									
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
	                                       		          <input type="hidden"  value = "" id = "customer_id_level1_hidden"  name="customer_id_level1_hidden"   /> 	
	                                       		          <input type="hidden"  value = "" id = "customer_code_level1_hidden"  name="customer_code_level1_hidden"    /> 		                                       									
				                                          <input value = "" id = "customer_name_level1" name="customer_name_level1" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>NVTT </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
	                                       		  
        			  			                          <input type="hidden"  value = "" id = "nvtt_id_hidden"  name="nvtt_id_hidden"    class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>             									
				                                          <input value = "" id = "nvtt_name" name="nvtt_name" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                                 		  
	                                        </div>
										     <div class="clear"></div>	
										      <div class="row " >
	                                       		  <div class="col-lg-2 no_margin_right" ><span>Ngày nhận toa </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
			                                       		   <div class="input-group input-append date" id="ngaynhantoa_datePicker">
												                <input type="text" class="form-control" name="ngaynhantoa"  id= "ngaynhantoa"/>
												                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
												            </div>
	                                       		  </div>
	                                       		  
	                                       		 <div class="col-lg-2 no_margin_right" ><span>Ngày nhận hàng </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
	                                       		          <div class="input-group input-append date" id="ngaynhanhang_datePicker">
												                <input type="text" class="form-control" name="ngaynhanhang"  id= "ngaynhanhang"/>
												                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
												            </div>
												            			                                       									
				                                          
	                                       		  </div>	                                          		  
	                                        </div>
	                                        
	                                        <div class="clear"></div>	
										      <div class="row " >
	                                       		  <div class="col-lg-2 no_margin_right" ><span>Số ngày gởi trể</span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "date_sent_late" name="date_sent_late" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>Ghi chú </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "notes" name="notes" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
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
							          <% for(int i = 1; i < 3; i++) {%> 
		                                <tr class="odd gradeX" id = "<%=i %>" >
		                                    <td id = "stt_<%=i %>">	<%=i %></td>
											
											<td>
												<input value = "" id = "masanpham_<%=i %>"    name="masanpham_<%=i %>"  class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "tensanpham_<%=i %>"   name="tensanpham_<%=i %>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
											   <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "soluong_<%=i %>"   name="soluong_<%=i %>" class="custom-input-debitor form-control currency" "/>
											
											</td>
											<td>
											  <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "sothung_<%=i %>"   name="sothung_<%=i %>" class="custom-input-debitor form-control currency"  />
											</td>
											
											<td>
											  <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "dongia_<%=i %>"   name="dongia_<%=i %>" class="custom-input-debitor form-control currency"  />
											
											</td>
											
											<td>
											  <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "thanhtien_<%=i %>"   name="thanhtien_<%=i %>" class="custom-input-debitor form-control currency"  />
											
											</td>
											<td>
											   <div style="padding-top: 4px; padding-left: 2px; padding-right: 2px">
											        <button type="button" class="btn btn-success btn-sm" onclick="addRow(<%=i %>)"   id ="addRow_<%=i %>"><span class="glyphicon glyphicon-plus"></span></button>
                                                    <button type="button" class="btn btn-danger btn-sm"  onclick="moveRow(<%=i %>)"  id ="moveRow_<%=i %>"><span class="glyphicon glyphicon-remove"></span></button>											
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
<script type="text/javascript" src="js/hoadon_nhap.js" ></script>   
<script type="text/javascript">
function formattedNumberField(event){
	 var x = event.which || event.keyCode;
	 console.log(x);
	 return false;
}
function getjob(){
	
	 var user_id                  = document.getElementById("user_id").value ;
	 $.ajax({  		
	       type: "GET",
           url     : 'getJobCaptureAction?user_id='+ user_id,	          
           data    : "",
           success : function(responseText) {	
        	  console.log(responseText);
        	 checkGetJob(false);
        	 var invoice_data_id = responseText[0].responseText;
        	 var id              = responseText[0].id;
        	 document.getElementById("id").value               = id;
       	     document.getElementById("management_id").value    = id ;
       	     document.getElementById("invoice_type_id").value  = invoice_data_id ;
       	     var imagePath_hidden ="";
       	     var file_path        = responseText[0].file_path.split("/DX_Images/");
       	     var file_name        = responseText[0].file_name;
       	     if(file_path.length>1){
       	    	imagePath_hidden = file_path[1] +"/"+file_name;
       	     }
       	    
       	     var url = window.location.href.toString();
       	     if(url.includes("localhost")){
       	    	 imagePath_hidden = "http://nv.dongxanhvn.com:8077/DX_Images/test_image2.jpg";
       	     }else{
       	    	imagePath_hidden = "http://nv.dongxanhvn.com:8077/DX_Images/"+imagePath_hidden;
       	     }
       	    
       	     $('#viewer').iviewer('loadImage', imagePath_hidden);
	       	
           }
	   });

}




function checkGetJob(flag){

		
		$("#id").prop('readonly', flag);
		$("#management_id").prop('readonly', flag);
		$("#invoice_type_id").prop('readonly', flag);
		$("#user_id").prop('readonly', flag);
		$("#user_name").prop('readonly', flag);
		$('#cbb_loaibangke').attr("disabled", flag); 
		
		$("#maKH").prop('readonly', flag);
		$("#tenKH").prop('readonly', flag);
		$("#tenbangke").prop('readonly', flag);
		
		$("#customer_name_level1").prop('readonly', flag);
		$("#nvtt_name").prop('readonly', flag);
		 
		$("#ngaynhantoa").prop('readonly', flag);
		$("#ngaynhanhang").prop('readonly', flag);
		$("#date_sent_late").prop('readonly', flag);
		$("#notes").prop('readonly', flag);
		
		$("#btn_nhapbangke").prop('disabled', !flag);
		$("#btn_xoabangke").prop('disabled', flag);
		$("#btn_Luubangke").prop('disabled', flag);
	
		  $('#table_position > tbody  > tr').each(function() {		
			  var id        =   this.getAttribute("id");			 
			  $("#masanpham_"+id).prop('readonly', flag);
			  $("#tensanpham_"+id).prop('readonly', flag);
			  $("#soluong_"+id).prop('readonly', flag);
			  $("#sothung_"+id).prop('readonly', flag);
			  $("#dongia_"+id).prop('readonly', flag);
			  $("#thanhtien_"+id).prop('readonly', flag);
			  $("#addRow_"+id).prop('disabled', flag);
			  $("#moveRow_"+id).prop('disabled', flag);
			 
		  });
		
	
}
function resetData(){
	 document.getElementById("id").value="" ;
	 document.getElementById("management_id").value="" ;
	 document.getElementById("invoice_type_id").value="" ;	
	 
	  document.getElementById("customer_id_hidden").value ="" ;
	  document.getElementById("maKH").value="" ;
	  document.getElementById("tenKH").value="" ;
	  
	  
	  document.getElementById("customer_id_level1_hidden").value ="" ;
	  document.getElementById("customer_code_level1_hidden").value ="" ;
	  document.getElementById("customer_name_level1").value  ="" ;
	 
	  document.getElementById("nvtt_id_hidden").value ="" ;
	  document.getElementById("nvtt_name").value ="" ;
	 
	  
	  document.getElementById("ngaynhantoa").value="" ;
	  document.getElementById("ngaynhanhang").value ="";
	  document.getElementById("date_sent_late").value ="";
      document.getElementById("notes").value ="";
      getjob();
	 
	
}
var maxRow =0;
function saveData(){
	maxRow =0;
	 if (checktable()) {
			
		  var id_invoice               = parseInt(document.getElementById("id").value) ;//id
		  var management_id            = parseInt(document.getElementById("management_id").value) ;//management_id
		  var invoice_type_id          = parseInt( document.getElementById("invoice_type_id").value) ;//invoice_type_id
		  var invoice_type             = document.getElementById("cbb_loaibangke").value ; //invoice_type
		  
		  
		  var customer_id              = document.getElementById("customer_id_hidden").value ; //customer_id
		  var customer_code            = document.getElementById("maKH").value ; //customer_code
		  var customer_name            = document.getElementById("tenKH").value ;//customer_name
		  
		  
		  var customer_id_level1       = document.getElementById("customer_id_level1_hidden").value ; //customer_id_level1
		  var customer_code_level1     = document.getElementById("customer_code_level1_hidden").value ; //customer_code_level1
		  var customer_name_level1     = document.getElementById("customer_name_level1").value ;//customer_name_level1	  
		 
		  var staff_id                 = document.getElementById("nvtt_id_hidden").value ;//staff_id
		  var staff_name               = document.getElementById("nvtt_name").value ;//staff_name
		 
		  
		  var date_company_received    = document.getElementById("ngaynhantoa").value ;//date_company_received
		  var date_product_received    = document.getElementById("ngaynhanhang").value ;//date_product_received
		  var date_sent_late           = document.getElementById("date_sent_late").value ;//date_sent_late
		  var notes                    = document.getElementById("notes").value ;//notes
		 
		  
		
		  var product_ids="";//product_ids
		  var product_names="";
		  var total_boxs="";
		  var quantitys="";
		  var product_prices="";
		  var total_prices="";
		  var sum_total_price=0;
		 
		
		
		
		  if(date_company_received.length==10){
			  date_company_received = date_company_received.substring(6,10)+"-"+date_company_received.substring(3,5)+"-"+date_company_received.substring(0,2)+"T00:00:00";		
		  }
		  if(date_product_received.length==10){
			  date_product_received = date_product_received.substring(6,10)+"-"+date_product_received.substring(3,5)+"-"+date_product_received.substring(0,2)+"T00:00:00";		
		  }
		  var row =0;
		  $('#table_position > tbody  > tr').each(function() {		
			  var id        =   this.getAttribute("id");
			  var masp      =   document.getElementById("masanpham_"+id).value;
			  var tensp     =   document.getElementById("tensanpham_"+id).value;
			  var soluong   =   document.getElementById("soluong_"+id).value;
			  var sothung   =   document.getElementById("sothung_"+id).value;
			  var dongia    =   document.getElementById("dongia_"+id).value;
			  var thanhtien =   document.getElementById("thanhtien_"+id).value;
			  if(dongia!="" && thanhtien !="" && tensp !=""){
				 
				  product_ids      = product_ids        +masp       +"`";
				  product_names    = product_names      +tensp      +"`";			 
				  quantitys        = quantitys          +soluong    +"`";  
				  total_boxs       = total_boxs         +sothung    +"`";
				  product_prices   = product_prices     +dongia     +"`";
				  total_prices     = total_prices       +thanhtien  +"`";
				  sum_total_price = parseFloat(sum_total_price) + parseFloat(thanhtien);
			  }
			 console.log('management_id==='+management_id);
			 console.log('customer_id==='+customer_id);
			 console.log('customer_id_level1==='+customer_id_level1);
			 console.log('staff_id==='+staff_id);
			  row++;
			  if(row==maxRow){
				  $.ajax({
					  async: false,
			          dataType: 'json',
			          contentType: "application/json",
				       type: "POST",
			          url : 'saveJobCaptureAction',	          
			          data: JSON.stringify({
			        	  invoice_data:{			        		 
			        		  "id": id_invoice,
			        		  "management_id": management_id,
			        		  "invoice_type_id": 1,
			        		  "invoice_type": invoice_type,
			        		  "customer_id": customer_id,
			        		  "customer_code": customer_code,
			        		  "customer_name": customer_name,
			        		  "customer_id_level1": 1,
			        		  "customer_code_level1": customer_code_level1,
			        		  "customer_name_level1": customer_name_level1,
			        		  "staff_id": 23,
			        		  "staff_name": staff_name,
			        		  "date_company_received":date_company_received,
			        		  "date_product_received": date_product_received,
			        		  "date_sent_late": 0,
			        		  "notes": notes,
			        		  "product_ids": product_ids,
			        		  "product_names": product_names,
			        		  "total_boxs": total_boxs,
			        		  "quantitys": quantitys,
			        		  "total_prices": total_prices,
			        		  "sum_total_price":sum_total_price
			        		    
					 	    } 
					 	         
					   	}) ,	           
			          success : function(responseText) {
			          
			           resetData();
			       	   console.log(responseText);
			       	  
			          }
				   });
			  }
			
			  
			 
		 });
		
		
		 
		  
		  
	}
	
	  
}

function checktable(){
	  var row =0;
	  var flag =true;
	  var date_company_received    = document.getElementById("ngaynhantoa").value ;
	  var date_product_received    = document.getElementById("ngaynhanhang").value ;
	  if(date_company_received.length !=10){
		  alert("Ngày nhận toa không được rỗng!");
			flag = false;
			return false;
	  }
	  
	  if(date_product_received.length !=10){
		  alert("Ngày nhận hàng không được rỗng!");
			flag = false;
			return false;
	  }
	  $('#table_position > tbody  > tr').each(function() {		
		  row ++;
		  maxRow ++;
		  var id        =   this.getAttribute("id");
		  var masp      =   document.getElementById("masanpham_"+id).value;
		  var tensp     =   document.getElementById("tensanpham_"+id).value;
		  var soluong   =   document.getElementById("soluong_"+id).value;
		  var sothung   =   document.getElementById("sothung_"+id).value;
		  var dongia    =   document.getElementById("dongia_"+id).value;
		  var thanhtien =   document.getElementById("thanhtien_"+id).value;
		  
		  var result ="";
		   if(masp===""){
			    result = result +" mã sản phẩm không được rỗng.\n";
		    }
			if(tensp===""){
				 result = result +" tên sản phẩm không được rỗng.\n";	  
		    }
			if(soluong==="" &&  sothung===""){
				 result = result +" số lượng hoặc số thùng không được rỗng.\n";
			}
			if(dongia===""){
				 result = result +" đơn giá không được rỗng.\n";
			}
			if(thanhtien===""){
				 result = result +" thành tiền không được rỗng.\n";
			}
			
			if (result!="") {
				result =  "Dòng "+ row +" không hợp lệ \n"+result;
				alert(result);
				flag = false;
				return false;
			} 
	 });
	  return flag;
	
	
	  
}
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
	  
	  row.id        = time_id;
	  stt.id        = "stt_"+time_id;
	  stt.innerHTML = "";
	
	  masp.innerHTML      = "<input  value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'masanpham_"+time_id+"'     name='masanpham_"+time_id+"'  class='custom-input-debitor form-control '/>";
	  tensp.innerHTML     = "<input  value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'tensanpham_"+time_id+"'    name='tensanpham_"+time_id+"' class='custom-input-debitor form-control '/>";
	  soluong.innerHTML   = "<input  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'soluong_"+time_id+"'       name='soluong_"+time_id+"'    class='custom-input-debitor form-control currency'/>";
	  sothung.innerHTML   = "<input  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'sothung_"+time_id+"'       name='sothung_"+time_id+"'    class='custom-input-debitor form-control currency'/>";
	  dongia.innerHTML    = "<input  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'dongia_"+time_id+"'        name='dongia_"+time_id+"'     class='custom-input-debitor form-control currency'/>";
	  thanhtien.innerHTML = "<input  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'thanhtien_"+time_id+"'     name='thanhtien_"+time_id+"'  class='custom-input-debitor form-control currency'/>";
	      
	  var add_remove  = "<div style='padding-top: 4px; padding-left: 2px; padding-right: 2px'>  ";   
	      add_remove  +="<button type='button' class='btn btn-success btn-sm' onclick='addRow("+time_id+")'   id ='addRow_"+time_id+"' ><span class='glyphicon glyphicon-plus' ></span></button> ";
	      add_remove  +="<button type='button' class='btn btn-danger btn-sm'  onclick='moveRow("+time_id+")'  id ='moveRow_"+time_id+"' ><span class='glyphicon glyphicon-remove'></span></button>";
	      add_remove  +="</div>";
	      
	  addremove.innerHTML =add_remove;
	  
	    var nebenkosten = document.getElementById("masanpham_"+time_id);
		var nebenkosten_betrag = document.getElementById("tensanpham_"+time_id);
		var nebenkosten_type = document.getElementById("soluong_"+time_id);
		
		new LookupKhachHang(nebenkosten, nebenkosten_betrag, nebenkosten_type, null, 4, {
			list : <%=data_postion%>
		});
	
	if(id!=0){
		stt =0;
		  $('#table_position > tbody  > tr').each(function() {
				  stt++;	
				  document.getElementById("stt_"+this.getAttribute("id")).innerHTML  = stt;        
				 
				 
			 });
	}
	  
	
}

function moveRow(id){
	var result = confirm("bạn có chắt chắn chắn xóa dòng này?");
	if (result) {
		var stt   = -1;
		var index = -1;	
		 $('#table_position > tbody  > tr').each(function() {
			 stt++;
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
$(document).ready(function() {
	var imagePath = "http://nv.dongxanhvn.com:8077/DX_Images/test_image.jpg";
	checkGetJob(true);
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
	
	  var table = $('#table_position').DataTable({
		  "scrollY": "360px",
		  "scrollCollapse": true,	
		  
	   });
	
	  $('#ngaynhantoa_datePicker').datepicker({
           format: 'dd/mm/yyyy'
       }).on('changeDate', function(e) {
           // Revalidate the date field
           $('#eventForm').formValidation('revalidateField', 'eventDate');
       });		   
       $('#ngaynhanhang_datePicker').datepicker({
           format: 'dd/mm/yyyy'
       }).on('changeDate', function(e) {
           // Revalidate the date field
           $('#eventForm').formValidation('revalidateField', 'eventDate');
       });
                                   
       
  
      var iv = $("#viewer").iviewer({
    	   src : imagePath,
           update_on_resize: true,
           zoom_animation: true,
           mousewheel: false,
           onMouseMove: function(ev, coords) { return true },
           onStartDrag: function(ev, coords) { return true },
           onDrag: function(ev, coords) { return true }
      });
      $("#in").click(function(){ iv.iviewer('zoom_by', 1); });
      $("#out").click(function(){ iv.iviewer('zoom_by', -1); });
      $("#fit").click(function(){ iv.iviewer('fit'); });
      $("#orig").click(function(){ iv.iviewer('set_zoom', 100); });
      $("#update").click(function(){ iv.iviewer('update_container_info'); });
    
});
        var customer_id_hidden            = document.getElementById("customer_id_hidden");
		var maKH                          = document.getElementById("maKH");
		var tenKH                         = document.getElementById("tenKH");
		var customer_name_level1          = document.getElementById("customer_name_level1");
		var nvtt_name                     = document.getElementById("nvtt_name");
		var customer_code_level1_hidden   = document.getElementById("customer_code_level1_hidden");
		var nvtt_id_hidden                = document.getElementById("nvtt_id_hidden"); 
	 
		
		var khachhang_ma = new LookupKhachHang(maKH, tenKH, customer_name_level1, nvtt_name,customer_code_level1_hidden,nvtt_id_hidden,customer_id_hidden, 1, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		
		var khachhang_ten = new LookupKhachHang(tenKH, maKH, customer_name_level1, nvtt_name,customer_code_level1_hidden,nvtt_id_hidden,customer_id_hidden, 2, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		
		$( "#maKH" ).keyup(function(e) {
			  var code = (e.keyCode || e.which);      
		        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
		            return;
		        }else{
		        	 var user_id                  = document.getElementById("maKH").value ;
		        	 loolupKhachHang(user_id ,khachhang_ma);
		        }
			});
		
		$( "#tenKH" ).keyup(function(e) {
			  var code = (e.keyCode || e.which);      
		        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
		            return;
		        }else{
		        	 var tenKH                  = document.getElementById("tenKH").value ;
		        	 loolupKhachHang(tenKH,khachhang_ten);
		        }
			});
		
		
		function loolupKhachHang(name ,khachhang){
			 $.ajax({  		
			       type: "GET",
		           url     : 'lookupCaptureCusStaffAction?search_cus='+ name,	          
		           data    : "",
		           success : function(responseText) {
		        	
		        	 var stt =0;
		        	 var resultjson = [];
	 	        	 for (i in responseText) { 
	 	        		 console.log(responseText);
	 	        		data ="";	
	 	        		data = data +responseText[i][0]+"|";//customer_id
	 	        		data = data +responseText[i][1]+"|";//invoice_name			 	        	
	 	        		data = data +responseText[i][2]+"|";//customer_name
	 	        		data = data +responseText[i][3]+"|";//customer_id_level1
	 	        		data = data +responseText[i][4]+"|";//customer_name_level1
	 	        		data = data +responseText[i][5]+"|";//user_id
	 	        		data = data +responseText[i][6]+"";//user_name
	 	        		resultjson.push(data);
	 	        		stt++;
	 	        		
	 	        		}
	 	        	
	 	        	if(stt>1){
	 	        		
	 	        		khachhang.list = resultjson ;
	        		}else{
	        			
	        		}
	 	        	
		        	
		           }
			   });
			 
		}
		
		  $('#table_position > tbody  > tr').each(function() {
			    var nebenkosten = document.getElementById("masanpham_"+this.getAttribute("id"));
				var nebenkosten_betrag = document.getElementById("tensanpham_"+this.getAttribute("id"));
				var nebenkosten_type = document.getElementById("soluong_"+this.getAttribute("id"));
				
				new LookupKhachHang(nebenkosten, nebenkosten_betrag, nebenkosten_type, null, 4, {
					list : <%=data_postion%>
				});
				
				
				new LookupKhachHang(nebenkosten_type,nebenkosten, nebenkosten_betrag, null, 4, {
					list : <%=data_postion%>
				});
				
				new LookupKhachHang(nebenkosten_betrag,nebenkosten_type,nebenkosten, null, 4, {
					list : <%=data_postion%>
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

    
 <style type="text/css">

input.currency {
    text-align: right;
    padding-right: 0px;
}

 
 
 </style>
    
    
    
</body>
</html>
