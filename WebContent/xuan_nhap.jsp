<%@page import="com.home.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.home.model.InvoiceType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html  lang="en">
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
<script src="js/moment.js"></script>
 

</head>
<body>
 <%
 
 List<InvoiceType> listInvoice =  (ArrayList<InvoiceType>) request.getAttribute("listInvoiceType");
 User userSes                  =  ( User) request.getAttribute("userSes");	
 %>
  
   <div id="header">  
		<div class="navbar nav_title" style="border: 0;">
			<a class="site_title" href="homeAction">
				<img width="27%" height="70%" alt="ĐỒNG XANH" src="./images/banner_dongxanh.png" style="padding-top: 10px ;">
			
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
        			  <input type="hidden" id="management_id" name="management_id" value="">
        			  <input type="hidden" id="invoice_type_id" name="invoice_type_id" value="">
        			
        			  
        			  <input type="hidden" id="user_id" name="user_id" value="<%= userSes.getId()%>">
        			  <input type="hidden" id="user_name" name="user_name" value="<%= userSes.getUserName()%>">
        			  
        			  
    	                <div class="well"><p align="center">
    	                        				            
				                 <button title="Alt +S" type="button" id ="btn_xoabangke"  class="btn btn-success" onclick="bad_images();">Xóa toa/bảng kê</button>			  
				            	 <button title="Alt +D" id ="btn_Luubangke"  class="btn btn-success" onclick="saveData();">Lưu toa/bảng kê</button>			  
                      	</div>
						<div id="wrapper" style="width : 100%; height : 100%; float: right">
							<!-- <div class="panel-heading" style="color: blue;font-weight: bold;">META DATA </div> -->
									<div class="metadata" style="z-index: 1">									    
									        <div class="row " >
	                                       		  <div class="col-lg-2" ><span>Loại bảng kê  </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
	                                       		        <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search" style="width: 100% ; height: 27px">
										           </select>
	                                       		  </div>  
	                                       		  
	                                       		  <div class="col-lg-2 no_margin_right" ><span>Ngày Cty nhận toa </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
	                                       		         <input value = "" id = "ngaynhantoa" name="ngaynhantoa" class="form-control"  tabindex="1" onkeyup="getSongay()"/>			
			                                       		  
	                                       		  </div>                              		  
	                                        </div>
	                                        
	                                        
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-2" ><span>Mã KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">	
	                                       		          <input type="hidden" id="customer_id_hidden" name="customer_id_hidden" value="789">		                                       									
				                                          <input value = "" id = "maKH" name="maKH" class="form-control" ondblclick = "getIdTag(this)" tabindex="2"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>Tên KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input value = "" id = "tenKH" name="tenKH" class="form-control" ondblclick = "getIdTag(this)" tabindex="3"/>				                                         
	                                       		  </div>	                                       		  
	                                        </div>
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-2" ><span>KH Cấp 1 </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">	
	                                       		          <input type="hidden"  value = "" id = "customer_id_level1_hidden"  name="customer_id_level1_hidden"   /> 	
	                                       		          <input type="hidden"  value = "" id = "customer_code_level1_hidden"  name="customer_code_level1_hidden"    /> 		                                       									
				                                          <input value = "" id = "customer_name_level1" name="customer_name_level1" class="form-control" ondblclick = "getIdTag(this)" tabindex="4"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-2" ><span>NVTT </span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
	                                       		  
        			  			                          <input type="hidden"  value = "" id = "nvtt_id_hidden"  name="nvtt_id_hidden"    class="form-control" ondblclick = "getIdTag(this)" tabindex="4"/>             									
				                                          <input value = "" id = "nvtt_name" name="nvtt_name" class="form-control" ondblclick = "getIdTag(this)" tabindex="5"/>				                                         
	                                       		  </div>	                                                 		  
	                                        </div>
										     <div class="clear"></div>	
										      <div class="row " >
	                                       		 <div class="col-lg-2 no_margin_right" ><span>Ngày cấp 1 giao toa</span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">
	                                       		   <input value = "" id = "ngaynhanhang" name="ngaynhanhang" class="form-control"  tabindex="6" onkeyup="getSongay()"/>	
	                                       		  </div>
	                                       		  
	                                       		  <div class="col-lg-2 no_margin_right" ><span>Số ngày gởi trể</span>  </div>	                                       		  
	                                       		  <div class="col-lg-4">			                                       									
				                                          <input onkeyup="getSongay()" value = "" type="number" id = "date_sent_late" name="date_sent_late" class="form-control" ondblclick = "getIdTag(this)" tabindex="7"/>				                                         
	                                       		  </div>	   
	                                       		                                            		  
	                                        </div>
	                                        
	                                        <div class="clear"></div>	
										      <div class="row " >
	                                       		
	                                       		 <div class="col-lg-2" ><span>Ghi chú </span>  </div>	                                       		  
	                                       		  <div class="col-lg-10">			                                       									
				                                          <input value = "" id = "notes" name="notes" class="form-control" ondblclick = "getIdTag(this)" tabindex="8"/>				                                         
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
							             <th class = "header-table-column  table-th" >STT</th>
							             <th class = "header-table-column  table-th " >Mã sản phẩm</th>
							             <th class = "header-table-column  table-th"  style="width: 200px!important;">Tên sản phẩm</th>
							             <th class = "header-table-column  table-th" >Số lượng</th>
							             <th class = "header-table-column  table-th" >Số thùng</th>
							             <th class = "header-table-column  table-th" >Đơn giá</th>
							             <th class = "header-table-column  table-th" style="width: 70px!important;">Thành tiền</th>
							            
							             <th class = "header-table-column  table-th" style="width: 80px"></th>
							            </tr>
							          </thead>
							          <tbody id ="tbody" >
							          <% 
							          int lc =2;
							          for(int i = 1; i < 15; i++) {%> 
		                                <tr class="odd gradeX" id = "<%=i %>" >
		                                    <td id = "stt_<%=i %>">	<%=i %></td>
											
											<td>
												<input value = "" id = "masanpham_<%=i %>"    name="masanpham_<%=i %>"  class="custom-input-debitor form-control" onkeyup="tinhtong(<%=i %>)" tabindex="<%= (i*6) +1+lc%>"/>
											</td>
											<td>
												<input value = "" id = "tensanpham_<%=i %>"   name="tensanpham_<%=i %>" class="custom-input-debitor form-control"  onkeyup="tinhtong(<%=i %>)" tabindex="<%= (i*6) +2+lc%>"/>
											</td>
											<td>
											   <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "soluong_<%=i %>"  onkeyup="tinhtong_sothung(<%=i %>)"  name="soluong_<%=i %>" class="custom-input-debitor form-control currency" tabindex="<%= (i*6) +3+lc%>"/>
											
											</td>
											<td>
											  <input type="hidden"  value = "" id = "sochai_1thung_<%=i %>"   name="sochai_1thung_<%=i %>">
											  <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "sothung_<%=i %>"  onkeyup="tinhtong(<%=i %>)"  name="sothung_<%=i %>" class="custom-input-debitor form-control currency" tabindex="<%= (i*6) +4+lc%>" />
											</td>
											
											<td>
											  <input type="number" value="" min="0" step="1" data-number-to-fixed="2" data-number-stepfactor="100" id = "dongia_<%=i %>"  onkeyup="tinhtong(<%=i %>)"  name="dongia_<%=i %>" class="custom-input-debitor form-control currency" tabindex="<%= (i*6) +5+lc%>" />
											
											</td>
											
											<td>
											  <input type="number" value="" min="0" step="any" data-number-to-fixed="2"  lang="en-150"  data-number-stepfactor="100" id = "thanhtien_<%=i %>"  onkeyup="tinhtong(<%=i %>)"  name="thanhtien_<%=i %>" class="custom-input-debitor form-control currency" tabindex="<%= (i*6) +6+lc%>" />
											
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

function getjob(){
	
	 var user_id                  = document.getElementById("user_id").value ;
	 $.ajax({  		
	       type: "GET",
           url     : 'getJobCaptureAction?user_id='+ user_id,	          
           data    : "",
           success : function(responseText) {	
        	 //  console.log("==================305=================");
        	 // console.log(responseText);
        	
        	 if(responseText.length>0){
        		 checkGetJob(false);
        		 var invoice_data_id = responseText[0].responseText;
            	 var id              = responseText[0].id;
            	 document.getElementById("id").value               = id;
           	     document.getElementById("management_id").value    = id ;
           	     var ngaynhantoa_date                              = responseText[0].created_time ;
           	     var ngaynhantoa ="";
           	     console.log(ngaynhantoa_date);
           	     if(ngaynhantoa_date.length>10){
           	    	ngaynhantoa = ngaynhantoa_date.substring(8,10)+"/"+ngaynhantoa_date.substring(5,7)+"/"+ngaynhantoa_date.substring(0,4);
           	        document.getElementById("ngaynhantoa").value      = ngaynhantoa ;
           	        document.getElementById("ngaynhanhang").value      = ngaynhantoa ;
           	     }
           	   
           	  
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
           	    document.getElementById("ngaynhantoa").focus(); 
        	 }else{
        		 checkGetJob(true);
        		 alert("Hết hóa đơn nhập!");
        	 }
        	
	       	
           }
	   });

}


function bad_images(){	
	var result = confirm("bạn có chắt chắn chắn xóa hóa đơn này?");
	if (result) {
		 var management_id                  = document.getElementById("management_id").value ;
		 $.ajax({  		
		       type: "GET",
	          url     : 'badJobCaptureAction?management_id='+ management_id,	          
	          data    : "",
	          success : function(responseText) {	
	        	  alert("Xóa bảng kê thành công!");
	        	  var url = window.location.href.toString();			        	
	      		  window.location.replace(url);
	          }
		   });

	}
	
}

function isValidDate(d) {
	  return d instanceof Date && !isNaN(d);
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
    
      $("#tbody").empty();  
      getjob();
	 
	
}

function getSelectedText(elementId) {
    var elt = document.getElementById(elementId);

    if (elt.selectedIndex == -1)
        return null;

    return elt.options[elt.selectedIndex].text;
}

var maxRow =0;
function saveData(){
	 maxRow =0;
	
	 if (checktable()) {
			
		  var id_invoice               = parseInt(document.getElementById("id").value) ;//id
		  var management_id            = parseInt(document.getElementById("management_id").value) ;//management_id
		  var invoice_type_id          = document.getElementById("cbb_loaibangke").value; 
		  var invoice_type             = getSelectedText("cbb_loaibangke"); //invoice_type
		 
		  
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
			        		  "invoice_type_id": invoice_type_id,
			        		  "invoice_type": invoice_type,
			        		  "customer_id": customer_id,
			        		  "customer_code": customer_code,
			        		  "customer_name": customer_name,
			        		  "customer_id_level1": customer_id_level1,
			        		  "customer_code_level1": customer_code_level1,
			        		  "customer_name_level1": customer_name_level1,
			        		  "staff_id": staff_id,
			        		  "staff_name": staff_name,
			        		  "date_company_received":date_company_received,
			        		  "date_product_received": date_product_received,
			        		  "date_sent_late": date_sent_late,
			        		  "notes": notes,
			        		  "product_ids": product_ids,
			        		  "product_names": product_names,
			        		  "total_boxs": total_boxs,
			        		  "quantitys": quantitys,
			        		  "total_prices": total_prices,
			        		  "unit_prices": product_prices,
			        		  "sum_total_price":sum_total_price
			        		    
					 	    } 
					 	         
					   	}) ,	           
			          success : function(responseText) {
			        	   var url = window.location.href.toString();			        	
			        		window.location.replace(url);
			     
			       
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
		    alert("Ngày nhận toa không được rỗng hoặc khác định dạng dd/mm/yyyy!");
			flag = false;
			return false;
	  }
	  
	  if(date_product_received.length !=10){
		  alert("Ngày nhận hàng không được rỗng hoặc khác định dạng dd/mm/yyyy!");
			flag = false;
			return false;
	  }
	 var date1 = new Date(date_company_received);
	 var date2 = new Date(date_product_received);
	 if(date1<date2){
		    alert("Ngày nhận toa phải lớn hơn ngày nhận hàng!");
			flag = false;
			return false;
	 }
	
	  $('#table_position > tbody  > tr').each(function() {		
		  row ++;
		  maxRow ++;
		  var id        =   this.getAttribute("id");
		  var masp      =   document.getElementById("masanpham_"+id).value.trim();
		  var tensp     =   document.getElementById("tensanpham_"+id).value.trim();
		  var soluong   =   document.getElementById("soluong_"+id).value.trim();
		  var sothung   =   document.getElementById("sothung_"+id).value.trim();
		  var dongia    =   document.getElementById("dongia_"+id).value.trim();
		  var thanhtien =   document.getElementById("thanhtien_"+id).value.trim();
		  if(masp!=""    || tensp!=""   ||  soluong!="" ||
			 sothung!="" ||  dongia!="" ||  thanhtien!=""  ){
			  
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
			  
		  }
		 
	 });
	  return flag;
	
	
	  
}
function addRow(id){
	var stt =1;
	var index =1;
	if(id==0){
		index=0;
	}
	if(id!=0){
		 $('#table_position > tbody  > tr').each(function() {
			 stt++;
			 if(this.getAttribute("id")==id){			
				index = stt;			
			 }
			 
		 });
	}
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
	
	  masp.innerHTML      = "<input  tabindex =''  value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'masanpham_"+time_id+"'     name='masanpham_"+time_id+"'  class='custom-input-debitor form-control ' onkeyup='tinhtong("+time_id+")'/>";
	  tensp.innerHTML     = "<input  tabindex =''  value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'tensanpham_"+time_id+"'    name='tensanpham_"+time_id+"' class='custom-input-debitor form-control '  onkeyup='tinhtong("+time_id+")'/>";
	  soluong.innerHTML   = "<input  tabindex =''  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'soluong_"+time_id+"'       name='soluong_"+time_id+"'    class='custom-input-debitor form-control currency'  onkeyup='tinhtong_sothung("+time_id+")'/>";
	  sothung.innerHTML   = "<input type='hidden'  value = '' id = 'sochai_1thung_"+time_id+"'   name='sochai_1thung_"+time_id+"'> <input  tabindex =''  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'sothung_"+time_id+"'       name='sothung_"+time_id+"'    class='custom-input-debitor form-control currency'  onkeyup='tinhtong("+time_id+")'/>";
	  dongia.innerHTML    = "<input  tabindex =''  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'dongia_"+time_id+"'        name='dongia_"+time_id+"'     class='custom-input-debitor form-control currency'/ onkeyup='tinhtong("+time_id+")'>";
	  thanhtien.innerHTML = "<input  tabindex =''  type='number' value = '' min='0' step='1' data-number-to-fixed='2' data-number-stepfactor='100' id = 'thanhtien_"+time_id+"'     name='thanhtien_"+time_id+"'  class='custom-input-debitor form-control currency'/ onkeyup='tinhtong("+time_id+")' >";
	      
	  var add_remove  = "<div style='padding-top: 4px; padding-left: 2px; padding-right: 2px; width: 80px'>  ";   
	      add_remove  +="<button type='button' class='btn btn-success btn-sm' onclick='addRow("+time_id+")'   id ='addRow_"+time_id+"' ><span class='glyphicon glyphicon-plus' ></span></button> ";
	      add_remove  +="<button type='button' class='btn btn-danger btn-sm'  onclick='moveRow("+time_id+")'  id ='moveRow_"+time_id+"' ><span class='glyphicon glyphicon-remove'></span></button>";
	      add_remove  +="</div>";
	      
	  addremove.innerHTML =add_remove;
	  
	  var sanpham_id   = time_id; 
	    var masp       = document.getElementById("masanpham_"+sanpham_id);
		var tensp      = document.getElementById("tensanpham_"+sanpham_id);
		var soluong    = document.getElementById("soluong_"+sanpham_id);
		var sothung    = document.getElementById("sothung_"+sanpham_id);
		var dongia     = document.getElementById("dongia_"+sanpham_id);
		var sochai_1thung = document.getElementById("sochai_1thung_"+sanpham_id);
		var sanpham_ma = new LookupKhachHang(masp, tensp, dongia, sochai_1thung,null,null,null,null, 5, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		
		var sanpham_ten = new LookupKhachHang(tensp, masp, dongia, sochai_1thung,null,null,null,null, 6, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		$( "#masanpham_"+sanpham_id ).keyup(function(e) {
			  var code = (e.keyCode || e.which);      
		        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
		            return;
		        }else{				        	
		        	 loolupSanPham(masp.value,sanpham_ma);
		        }
			});
		
		$( "#tensanpham_"+sanpham_id ).keyup(function(e) {
			  var code = (e.keyCode || e.which);      
		        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
		            return;
		        }else{				        	
		        	 loolupSanPham(tensp.value,sanpham_ten);
		        }
			});
		

	
	if(id>0){
		 var stt =0;
		  var lc =2;
		  var i=0;
		  $('#table_position > tbody  > tr').each(function() {
				  stt++;
				  i++;
				  document.getElementById("stt_"+this.getAttribute("id")).innerHTML  = stt;
				  var id               =  this.getAttribute("id");
				  var index_ma         = (parseInt(stt)*6) +1+lc;
				  var index_ten        = (parseInt(stt)*6) +2+lc;
				  var index_sl         = (parseInt(stt)*6) +3+lc;
				  var index_st         = (parseInt(stt)*6) +4+lc;
				  var index_dongia     = (parseInt(stt)*6) +5+lc;
				  var index_thanhtien  = (parseInt(stt)*6) +6+lc;
				 document.getElementById("masanpham_"+id).tabIndex  = index_ma;
				 document.getElementById("tensanpham_"+id).tabIndex = index_ten;
				 document.getElementById("soluong_"+id).tabIndex    = index_sl;
				 document.getElementById("sothung_"+id).tabIndex    = index_st;
				 document.getElementById("dongia_"+id).tabIndex     = index_dongia;
				 document.getElementById("thanhtien_"+id).tabIndex  = index_thanhtien;
				 
				 $("#soluong_"+id).keypress(function() {
					// alert("nguyen truong xuan");
					  //console.log( "Handler for .keypress() called." );
				 });
				
			 });
		  
		  $('input').keydown(function (e) {	    	  
			  console.log(e.which);
	    	  if (e.which === 13 ) {    		  
	    		  var tabindex = $(this).attr('tabindex');      		
	    		  if((tabindex-9)>=0 && (tabindex-9)%6==0){    			
	    			   var masanpham_id   = $(this).attr('id').substring(10);    		
	  		    	   var tensanpham_val = document.getElementById("tensanpham_"+masanpham_id).value;   		    	
	  		    	   if(tensanpham_val !=""){
	  		    		 tabindex  = tabindex +2;  		    		
	  		    	   }else{
	  		    		 tabindex  = tabindex +1;  		    	
	  		    	   }
			    	}else if((tabindex-9)>=0 && (tabindex-9)%6==2){
			    		tabindex  = tabindex + 4;		  
			    	}else {
			    		 tabindex  = tabindex + 1;		    
			    	}
	    		  $("[tabindex='"+tabindex+"']").focus();    		 
	  	    }
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
				index++;
			 }		 
		 });	
		  var table = document.getElementById("table_position");
		  table.deleteRow(index);	 
		  stt =0;
		  var lc =2;
		  var i=0;
		  $('#table_position > tbody  > tr').each(function() {
				  stt++;
				  i++;
				  document.getElementById("stt_"+this.getAttribute("id")).innerHTML  = stt;
				  var id               =  this.getAttribute("id");
				  var index_ma         = (parseInt(stt)*6) +1+lc;
				  var index_ten        = (parseInt(stt)*6) +2+lc;
				  var index_sl         = (parseInt(stt)*6) +3+lc;
				  var index_st         = (parseInt(stt)*6) +4+lc;
				  var index_dongia     = (parseInt(stt)*6) +5+lc;
				  var index_thanhtien  = (parseInt(stt)*6) +6+lc;
				 document.getElementById("masanpham_"+id).tabIndex  = index_ma;
				 document.getElementById("tensanpham_"+id).tabIndex = index_ten;
				 document.getElementById("soluong_"+id).tabIndex    = index_sl;
				 document.getElementById("sothung_"+id).tabIndex    = index_st;
				 document.getElementById("dongia_"+id).tabIndex     = index_dongia;
				 document.getElementById("thanhtien_"+id).tabIndex  = index_thanhtien;
				 
				 
			
				//masanpham.attr("tabindex",index_ma);
			 });
		
	}
	
}
$(document).ready(function() {
	var imagePath = "http://nv.dongxanhvn.com:8077/DX_Images/test_image.jpg";
/* 	checkGetJob(true); */
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
      $('.iviewer_rotate_right').attr('title','Ok');
    
      $(".iviewer_rotate_right").click(function() {
    	
    	});
    
      $(".iviewer_zoom_out").click(function() {
     	
     	});
      $(".iviewer_zoom_zero").click(function() {
     	
     	});
      $(".iviewer_rotate_left").click(function() {
     	
     	});
      $(".iviewer_rotate_right").click(function() {
     	
     	});
      
      
      lookupCaptureInvoiceTypeAction();
      getjob();
      
      $('#table_position > tbody  > tr').each(function() {
		  var sanpham_id      =this.getAttribute("id"); 
		    var masp          = document.getElementById("masanpham_"+sanpham_id);
			var tensp         = document.getElementById("tensanpham_"+sanpham_id);
			var soluong       = document.getElementById("soluong_"+sanpham_id);
			var sothung       = document.getElementById("sothung_"+sanpham_id);
			var dongia        = document.getElementById("dongia_"+sanpham_id);
			var sochai_1thung = document.getElementById("sochai_1thung_"+sanpham_id);
			  
			var sanpham_ma = new LookupKhachHang(masp, tensp, dongia, sochai_1thung,null,null,null,null, 5, {		
				  minChars: 1,
				  maxItems: 20,
				  autoFirst: true
			});
			
			var sanpham_ten = new LookupKhachHang(tensp, masp, dongia, sochai_1thung,null,null,null,null, 6, {		
				  minChars: 1,
				  maxItems: 20,
				  autoFirst: true
			});
			$( "#masanpham_"+sanpham_id ).keyup(function(e) {
				  var code = (e.keyCode || e.which);      
			        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
			            return;
			        }else{				        	
			        	 loolupSanPham(masp.value,sanpham_ma);
			        }
				});
			
			$( "#tensanpham_"+sanpham_id ).keyup(function(e) {
				  var code = (e.keyCode || e.which);      
			        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
			            return;
			        }else{				        	
			        	 loolupSanPham(tensp.value,sanpham_ten);
			        }
		   });
			
			 
			
      });
	  
      $('input').keydown(function (e) {    
    	  console.log(e.which);
    	  if (e.which === 13 ) {    		  
    		  var tabindex = $(this).attr('tabindex');      		
    		  if((tabindex-9)>=0 && (tabindex-9)%6==0){    			
    			   var masanpham_id   = $(this).attr('id').substring(10);    		
  		    	   var tensanpham_val = document.getElementById("tensanpham_"+masanpham_id).value;   		    	
  		    	   if(tensanpham_val !=""){
  		    		 tabindex  = tabindex +2;  		    		
  		    	   }else{
  		    		 tabindex  = tabindex +1;  		    	
  		    	   }
		    	}else if((tabindex-9)>=0 && (tabindex-9)%6==2){
		    		tabindex  = tabindex + 4;		  
		    	}else {
		    		 tabindex  = tabindex + 1;		    
		    	}
    		  $("[tabindex='"+tabindex+"']").focus();    		 
  	    }
     });  
    
      $(document).on('keydown',function(e) {
    	 
    	  if(e.altKey && event.which ==83){
    		  saveData();
    	  }
    	  if(e.altKey && event.which ==68){
    		  bad_images();
    	  }
    	  
    	  if(e.altKey && event.which ==18){    		
    		//  $('#zoom_in').click(function() { zoom_page(10, $(this)) });
    	
    	  }
    
    	});
      
    
      
});
        var customer_id_hidden            = document.getElementById("customer_id_hidden");
		var maKH                          = document.getElementById("maKH");
		var tenKH                         = document.getElementById("tenKH");
		
		var nvtt_name                     = document.getElementById("nvtt_name");		
		var nvtt_id_hidden                = document.getElementById("nvtt_id_hidden"); 
		
		
		var customer_id_level1_hidden     = document.getElementById("customer_id_level1_hidden");
		var customer_code_level1_hidden   = document.getElementById("customer_code_level1_hidden");
		var customer_name_level1          = document.getElementById("customer_name_level1");
		
		var khachhang_ma = new LookupKhachHang(maKH, tenKH, customer_name_level1, nvtt_name,customer_code_level1_hidden,nvtt_id_hidden,customer_id_hidden,customer_code_level1_hidden, 1, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		
		var khachhang_ten = new LookupKhachHang(tenKH, maKH, customer_name_level1, nvtt_name,customer_code_level1_hidden,nvtt_id_hidden,customer_id_hidden,customer_code_level1_hidden, 2, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		
		var khachhang_nvtt = new LookupKhachHang(nvtt_name, nvtt_id_hidden, null, null,null,null,null,null, 4, {		
			  minChars: 1,
			  maxItems: 20,
			  autoFirst: true
		});
		
		var khachhang_cap1 = new LookupKhachHang(customer_name_level1, customer_id_level1_hidden, customer_code_level1_hidden, null,null,null,null,null, 3, {		
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
		$( "#customer_name_level1" ).keyup(function(e) {
			  var code = (e.keyCode || e.which);      
		        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
		            return;
		        }else{
		        	 var customer_name_level1                  = document.getElementById("customer_name_level1").value ;
		        	 loolupKhachHangCap1(customer_name_level1,khachhang_cap1);
		        }
			});
		
		$( "#nvtt_name" ).keyup(function(e) {
			  var code = (e.keyCode || e.which);      
		        if (code === 37 || code === 38 || code === 39 || code === 40 || code === 27 || code === 13) {
		            return;
		        }else{
		        	 var nvtt_name                  = document.getElementById("nvtt_name").value ;
		        	 loolupKhachHang_nvtt(nvtt_name,khachhang_nvtt);
		        }
			});
		
		
		function loolupKhachHang_nvtt(name ,khachhang){
			 $.ajax({  		
			       type: "GET",
		           url     : 'lookupCaptureStaffAction?search_start='+ name,	          
		           data    : "",
		           success : function(responseText) {
		        	
		        	 var stt =0;
		        	 var resultjson = [];
	 	        	 for (i in responseText) { 
	 	        		 
	 	        	//	 console.log(responseText);
	 	        		data ="";	
	 	        		data = data +responseText[i][0]+"|";//nvtt_id_hidden
	 	        		data = data +responseText[i][1]+"";//nvtt_name	
	 	        		
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
		
		function loolupKhachHangCap1(name ,khachhang){
			 $.ajax({  		
			       type: "GET",
		           url     : 'lookupCaptureCusAction?search_cus='+ name,	          
		           data    : "",
		           success : function(responseText) {
		        	
		        	 var stt =0;
		        	 var resultjson = [];
	 	        	 for (i in responseText) { 
	 	        		 
	 	        		// console.log(responseText);
	 	        		data ="";	
	 	        		data = data +responseText[i][0]+"|";//customer_id_level1_hidden
	 	        		data = data +responseText[i][1]+"|";//customer_code_level1_hidden	
	 	        		data = data +responseText[i][2]+"";//customer_name_level1	
	 	        	
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
	 	        		data = data +responseText[i][1]+"|";//customer_code			 	        	
	 	        		data = data +responseText[i][2]+"|";//customer_name
	 	        		data = data +responseText[i][3]+"|";//customer_id_level1	 	        		
	 	        		data = data +responseText[i][5]+"|";//customer_name_level1
	 	        		data = data +responseText[i][6]+"|";//user_id
	 	        		data = data +responseText[i][7]+"";//user_name
	 	        		data = data +responseText[i][4]+"|";//customer_code_level1_hidden
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
		
		function loolupSanPham(name ,khachhang){
			 $.ajax({  		
			       type: "GET",
		           url     : 'lookupCaptureProductAction?search_product='+ name,	          
		           data    : "",
		           success : function(responseText) {
		        	// alert(responseText);
		        	 var stt =0;
		        	 var resultjson = [];
	 	        	 for (i in responseText) { 
	 	        		console.log(responseText[i]);
	 	        		data ="";	
	 	        		data = data +responseText[i][2]+"|";//masp
	 	        		data = data +responseText[i][1]+"|";//tensp	
	 	        		
	 	        		data = data +responseText[i][3]+"|";//dongia
	 	        		data = data +responseText[i][4]+"";//soluongtren1thung
	 	        		//alert( data);
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
function tinhtong_sothung(id){
	  var soluong   =   document.getElementById("soluong_"+id).value;
	  var sothung   =   document.getElementById("sothung_"+id).value;
	  var dongia    =   document.getElementById("dongia_"+id).value;
	  var sochai_1thung    =   document.getElementById("sochai_1thung_"+id).value;
	
	  if(sochai_1thung.trim()!="" &&  soluong.trim()!=""){
		  try {				 
			  var kq = parseFloat(soluong) / parseFloat(sochai_1thung);
			  document.getElementById("sothung_"+id).value = kq;
		} catch (e) {
			// TODO: handle exception
		}
		
	  }
	  
	  if(dongia.trim()!="" &&  soluong.trim()!=""){
		  try {				 
			  var kq = parseFloat(dongia)*parseFloat(soluong);
			
			//  kq = kq.toLocaleString();
			  document.getElementById("thanhtien_"+id).value = kq;
		} catch (e) {
			// TODO: handle exception
		}
		
	  }
}
function tinhtong(id){
	
	
	  var soluong   =   document.getElementById("soluong_"+id).value;
	  var sothung   =   document.getElementById("sothung_"+id).value;
	  var dongia    =   document.getElementById("dongia_"+id).value;
	  console.log("1174============="+id);
	  if(dongia.trim()!="" &&  soluong.trim()!=""){
		  try {				 
			  var kq = parseFloat(dongia)*parseFloat(soluong);
			
			  document.getElementById("thanhtien_"+id).value = kq;
		} catch (e) {
			// TODO: handle exception
		}
		
	  }
}
   function getday(ngaynhantoa){
	   var date_val = ngaynhantoa.value.replace(/[^0-9]/g, '');
	   if(date_val.length >=8){
		   if(!moment(date_val, 'DDMMYYYY',true).isValid() )	{
			   alert("ngày không đúng đinh dạng(dd/mm/YYYY)!");
			   ngaynhantoa.focus();
			   return  false;
		   }else{
			   ngaynhantoa.value = date_val.substr(0,2)+"/"+date_val.substr(2,2)+"/"+date_val.substr(4,4);
			   return true;
		   }
		  
	   }
	   return false;
	  
	   
   }
   function getSongay(){	  
	   try {
		     var  ngaynhantoa  = document.getElementById("ngaynhantoa").value ;
		     var  ngaynhanhang = document.getElementById("ngaynhanhang").value ;
		     var  flag =0;
		     if(ngaynhantoa.length>=8){
		    	if(getday(document.getElementById("ngaynhantoa"))){
		    		 flag++;
		    	}
		    	
		     }
		     if(ngaynhanhang.length>=8){
		    	 
		    	 if(getday(document.getElementById("ngaynhanhang"))){
		    		 flag++;
		    	}
		    	
		     }
		     if(flag==2){
		    	 var from   = document.getElementById("ngaynhantoa").value.split("/");//date_company_received
				  var to    = document.getElementById("ngaynhanhang").value.split("/") ;//date_product_received
				  
				  var date1 =  new Date(from[2], from[1]-1, from[0]);
				  var date2 =  new Date(to[2], to[1]-1, to[0]);
				  var songay = (date1.getTime() -  date2.getTime() )/(1000*24*3600);
				  if(songay<=0){
					  songay="";
				  }
				  document.getElementById("date_sent_late").value =songay;
				
		     }
		     
		} catch (e) {
			// TODO: handle exception
		}
	     
	}
   
   function lookupCaptureInvoiceTypeAction(){ 
	   	 $.ajax({  		
	   	       type: "GET",
	              url     : "lookupCaptureInvoiceTypeAction",	          
	              data    : "",
	              success : function(responseText) {
	            	  var cbb_nvtt = document.getElementById("cbb_loaibangke");
	            	  console.log("============cbb_loaibangke===============");
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].invoiceType;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  }
	              }
	   	   });
}


</script> 

    
 <style type="text/css">

input.currency {
    text-align: right;
    padding-right: 0px;
}

 .table-wrapper-scroll-y {
display: block;
height: 400px;
overflow-y: auto;
-ms-overflow-style: -ms-autohiding-scrollbar;
}


 
 </style>
    
    
    
</body>
</html>
