<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ include file="header.jsp"%> 
<%@ include file="user_profile.jsp"%> 
<%@ include file="menu.jsp"%>
<link href="css/hoadon_thongke.css" type="text/css" rel="stylesheet">
<!-- page content -->
<div class="right_col" role="main">

	<div class="container">
	
	   <ul class="nav nav-tabs">
	        <li class="active class-font_14"><a data-toggle="tab" href="#thongkehoadon" onclick="ThongKeHoaDon(1)">Thống kê hóa đơn</a></li>
		    <li class=" class-font_14"><a data-toggle="tab" href="#mat_hang_cap_1"      onclick="ThongKeHoaDon(2)">Mặt hàng cấp I</a></li>
		    <li class="class-font_14"><a data-toggle="tab" href="#tong_hop_theo_nvtt"   onclick="ThongKeHoaDon(3)">Tổng hợp theo NVTT</a></li>
		    <li class="class-font_14"><a data-toggle="tab" href="#tong_hop_theo_cap_2"  onclick="ThongKeHoaDon(4)">Tổng hợp theo cấp 2 </a></li>
		    <li class="class-font_14"><a data-toggle="tab" href="#chi_tiet_toa_cap_2"   onclick="ThongKeHoaDon(5)">Chi tiết toa cấp 2</a></li>
		    
	   </ul>
	   <div class="tab-content">
	   
	   <!--  Mo dau Thống kê hóa đơn -->
	    <div id="thongkehoadon" class="tab-pane fade in active">
	    
	    <div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">

				<div class="form-horizontal form_search"> 
			     <!--  <div  class="form-horizontal form_search"> -->
			      <div class="form-group">			      
			       <div class="title_width_8" >Ngày nhận từ </div>
				        <div class="value_width_10">
				            <div class="input-group input-append date" id="from_datePicker">
				                <input type="text" class="form-control" name="form_date_search"  id= "form_date_search"/>
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
				        </div>
			        
			        <div class="title_width_8" >Ngày nhận đến </div>
			        <div class="value_width_10">
			            <div class="input-group input-append date" id="to_datePicker">
			                <input type="text" class="form-control" name="to_date_search"  id= "to_date_search" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			        </div>
			        
			           <div class="title_width_8" >Loại bảng kê</div>
				        <div class="value_width_10">
				            <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search">
				                 <option value="0">All</option>	
			                   
				           </select>
				        </div>
			          
			          
			          
				        
			        <div class="button_width_12">			           
			            <button type="button" class="btn btn-success" onclick="getInvoiceDataFilterReport();">Lọc bảng kê</button>			         
			        </div> 
					        
			      </div>
			      
			       <div class="form-group">
			       
			           <div class="title_width_8" >NVTT</div>
				        <div class="value_width_10">
				            <select id="cbb_nvtt" name="cbb_nvtt"  class="cbb_search" onchange="loadCusByStaffInvoiceReport();">
				                 <option value="0">All</option>	
			                 
				           </select>
				        </div>
				        
				       
				        
				          <div class="title_width_8" >Khách hàng</div>
				        <div class="value_width_10">
				            <select id="cbb_khachhang" name="cbb_khachhang"  class="cbb_search">
				                 <option value="0">All</option>	
			               
				           </select>
				        </div>
				        
				         <div class="title_width_8" >Ngày gửi trể</div>
				        <div class="value_width_10">
				            <select id="cbb_ngaygoitre" name="cbb_ngaygoitre"  class="cbb_search">
				                 <option value="0">All</option>
				                 <option value="1">Trể </option>
				                 <option value="2">Không trể</option>	
			              
				           </select>
				        </div>
				        
				        
				        <div class="button_width_12">			           
			                 <button type="submit" class="btn btn-success" onclick="export_ThongKeHoaDon(1);" >Xuất ra excel</button>			         
			            </div> 
					           
			      </div>
			     
			     
			 </div>
			 
			 
			 
			 
			 
			 

			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	    
	             <span class="scroll left-scroll"> &#171; </span>
                 <span class="scroll right-scroll" onclick="right_scroll_detail()">&#187;</span>   
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:11%">Ma KH</th>		
		                <th class="table-th" style="text-align: center;width:11%">Tên bảng kê</th>
		                <th class="table-th" style="text-align: center;width:11%">Khách hàng cấp 1</th>		
		                <th class="table-th" style="text-align: center;width:11%">NVTT</th>		                                
		                <th class="table-th" style="text-align: center;width:11%">Ngày nhận hàng</th>
		                <th class="table-th" style="text-align: center;width:11%">Số ngày gởi trể</th>
		                
		              
		                <th class="table-th" style="text-align: center;width:11%">Ghi chú</th>
		                <th class="table-th" style="text-align: center;width:11%">Tên sản phẩm </th>
		                <th class="table-th" style="text-align: center;width:11%">Số lượng</th>
		                <th class="table-th" style="text-align: center;width:11%">Số thùng</th>		               
		                <th class="table-th" style="text-align: center;width:11%">Đơn giá , thành tiền</th>
		                <th class="table-th" style="text-align: center;width:11%">Xem </th>
		               
		            </tr>
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 1;i++){%>
			       		 <tr  >
			       		    <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                
			                <td></td>
			                <td></td>
			                
			            </tr>
		            <%} %>
		        </tbody>
		    </table>
        </div> 	    	   
				
				
			</div>
		</div>
		
		
		
		
	    
	    </div>
	    
	    
	    
	    
	    <!--  mo dau #mat_hang_cap_1 -->
	<div id="mat_hang_cap_1" class="tab-pane fade padding_left">
	    
	    <div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
			
			<div class="form-horizontal form_search"> 
			    
			      <div class="form-group">			      
			       <div class="title_width_8" >Ngày nhận từ </div>
				        <div class="value_width_10">
				            <div class="input-group input-append date" id="from_datePicker">
				                <input type="text" class="form-control" name="form_date_search"  id= "form_date_search"/>
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
				        </div>
			        
			        <div class="title_width_8" >Ngày nhận đến </div>
			        <div class="value_width_10">
			            <div class="input-group input-append date" id="to_datePicker">
			                <input type="text" class="form-control" name="to_date_search"  id= "to_date_search" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			        </div>
			        
			           <div class="title_width_8" >Loại bảng kê</div>
				        <div class="value_width_10">
				            <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search">
				                 <option value="0">All</option>	
			                   
				           </select>
				        </div>
			        <div class="button_width_12">			           
			            <button type="button" class="btn btn-success" onclick="ThongKeHoaDon(2);">Lọc bảng kê</button>			         
			        </div> 
			        
			         <div class="button_width_12">			           
			                 <button type="submit" class="btn btn-success" onclick="export_ThongKeHoaDon(2);" >Xuất ra excel</button>			         
			         </div> 
					        
			      </div>
			     
			 </div>
			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	    
	             <span class="scroll left-scroll"> &#171; </span>
                 <span class="scroll right-scroll" onclick="right_scroll_detail()">&#187;</span>   
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th mat_hang_cap_1_header_table" rowspan ="2">STT</th>		
		                <th class="table-th mat_hang_cap_1_header_table" style="text-align: center;width:10%"  rowspan ="2">Cấp 1</th>
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tổng KH có theo dõi</th>		
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tổng KH có gửi toa</th>		                                
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">% Toa gửi hợp lệ</th>
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Mặt hàng</th>
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tên hàng</th>		               
		                <th class="table-th" style="text-align: center;width:30%" colspan="3">Số thùng</th>	
		            </tr>
		            
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:11%">Trước kỳ</th>		
		                <th class="table-th" style="text-align: center;width:11%">Phát sinh</th>
		                <th class="table-th" style="text-align: center;width:11%">Tổng</th>		
		               
		            </tr>
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 10;i++){%>
			       		 <tr  >
			       		    <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			               
			                
			            </tr>
		            <%} %>
		        </tbody>
		    </table>
        </div> 	    	   
				
				
			</div>
		</div>
		
		
	    
	    
	    </div>
	    
	    
	     <!--  mo dau #tong_hop_theo_nvtt -->
	    <div id="tong_hop_theo_nvtt" class="tab-pane fade padding_left">
	     <div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
			
			<div class="form-horizontal form_search"> 
			    
			      <div class="form-group">			      
			       <div class="title_width_8" >Ngày nhận từ </div>
				        <div class="value_width_10">
				            <div class="input-group input-append date" id="from_datePicker">
				                <input type="text" class="form-control" name="form_date_search"  id= "form_date_search"/>
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
				        </div>
			        
			        <div class="title_width_8" >Ngày nhận đến </div>
			        <div class="value_width_10">
			            <div class="input-group input-append date" id="to_datePicker">
			                <input type="text" class="form-control" name="to_date_search"  id= "to_date_search" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			        </div>
			        
			           <div class="title_width_8" >Loại bảng kê</div>
				        <div class="value_width_10">
				            <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search">
				                 <option value="0">All</option>	
			                   
				           </select>
				        </div>
			        <div class="button_width_12">			           
			            <button type="button" class="btn btn-success" onclick="ThongKeHoaDon(3);">Lọc bảng kê</button>			         
			        </div> 
			        
			         <div class="button_width_12">			           
			                 <button type="submit" class="btn btn-success" onclick="export_ThongKeHoaDon(3);" >Xuất ra excel</button>			         
			         </div> 
			      </div>
			     
			 </div>
			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	    
	             <span class="scroll left-scroll"> &#171; </span>
                 <span class="scroll right-scroll" onclick="right_scroll_detail()">&#187;</span>   
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th mat_hang_cap_1_header_table" rowspan ="2">STT</th>	
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tên NVTT</th>		
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tổng khách quản lý</th>		                                
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Số KH chưa gửi toa nào</th>
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">KH có phát sinh toa trong ngày</th>		                           
		                <th class="table-th" style="text-align: center;width:30%" colspan="3">Doanh số (tr)</th>	
		            </tr>
		            
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:11%">Trước kỳ</th>		
		                <th class="table-th" style="text-align: center;width:11%">Phát sinh</th>
		                <th class="table-th" style="text-align: center;width:11%">Tổng</th>		
		               
		            </tr>
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 10;i++){%>
			       		 <tr  >
			       		    <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                
			                <td></td>
			                <td></td>
			                <td></td>
			               
			               
			                
			            </tr>
		            <%} %>
		        </tbody>
		    </table>
        </div> 	    	   
				
				
			</div>
		</div>
		
		
	    
	    </div>
	    
	    
	     <!--  mo dau tong_hop_theo_cap_2 -->
	    <div id="tong_hop_theo_cap_2" class="tab-pane fade padding_left">
	     <div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
			
			<div class="form-horizontal form_search"> 
			    
			      <div class="form-group">			      
			       <div class="title_width_8" >Ngày nhận từ </div>
				        <div class="value_width_10">
				            <div class="input-group input-append date" id="from_datePicker">
				                <input type="text" class="form-control" name="form_date_search"  id= "form_date_search"/>
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
				        </div>
			        
			        <div class="title_width_8" >Ngày nhận đến </div>
			        <div class="value_width_10">
			            <div class="input-group input-append date" id="to_datePicker">
			                <input type="text" class="form-control" name="to_date_search"  id= "to_date_search" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			        </div>
			        
			           <div class="title_width_8" >Loại bảng kê</div>
				        <div class="value_width_10">
				            <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search">
				                 <option value="0">All</option>	
			                   
				           </select>
				        </div>
			        <div class="button_width_12">			           
			            <button type="button" class="btn btn-success" onclick="ThongKeHoaDon(4);">Lọc bảng kê</button>			         
			        </div> 
			        
			         <div class="button_width_12">			           
			                 <button type="submit" class="btn btn-success" onclick="export_ThongKeHoaDon(4);" >Xuất ra excel</button>			         
			         </div> 
					        
			      </div>
			     
			 </div>
			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	    
	             <span class="scroll left-scroll"> &#171; </span>
                 <span class="scroll right-scroll" onclick="right_scroll_detail()">&#187;</span>   
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th mat_hang_cap_1_header_table" rowspan ="2">STT</th>		
		                <th class="table-th mat_hang_cap_1_header_table" rowspan ="2">MKH</th>
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tên bảng kê</th>		
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">Tổng toa đã gửi</th>		                                
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">NVTT</th>
		                <th class="table-th mat_hang_cap_1_header_table"  rowspan ="2">% Toa hợp lệ</th>		                       
		                <th class="table-th" style="text-align: center;width:30%" colspan="3">Doanh số (tr)</th>	
		            </tr>
		            
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:11%">Trước kỳ</th>		
		                <th class="table-th" style="text-align: center;width:11%">Trong kỳ</th>
		                <th class="table-th" style="text-align: center;width:11%">Tổng</th>		
		               
		            </tr>
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 10;i++){%>
			       		 <tr  >
			       		    <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			              
			            </tr>
		            <%} %>
		        </tbody>
		    </table>
        </div> 	    	   
				
				
			</div>
		</div>
		
		
	    
	    </div>
	    
	    
	     <!--  mo dau chi_tiet_toa_cap_2 -->
	    <div id="chi_tiet_toa_cap_2" class="tab-pane fade padding_left">
	    <div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
			
			<div class="form-horizontal form_search"> 
			    
			      <div class="form-group">			      
			       <div class="title_width_8" >Ngày nhận từ </div>
				        <div class="value_width_10">
				            <div class="input-group input-append date" id="from_datePicker">
				                <input type="text" class="form-control" name="form_date_search"  id= "form_date_search"/>
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
				        </div>
			        
			        <div class="title_width_8" >Ngày nhận đến </div>
			        <div class="value_width_10">
			            <div class="input-group input-append date" id="to_datePicker">
			                <input type="text" class="form-control" name="to_date_search"  id= "to_date_search" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			        </div>
			        
			           <div class="title_width_8" >Loại bảng kê</div>
				        <div class="value_width_10">
				            <select id="cbb_loaibangke" name="cbb_loaibangke"  class="cbb_search">
				                 <option value="0">All</option>	
			                   
				           </select>
				        </div>
			        <div class="button_width_12">			           
			            <button type="button" class="btn btn-success" onclick="ThongKeHoaDon(5);">Lọc bảng kê</button>			         
			        </div> 
			        
			         <div class="button_width_12">			           
			                 <button type="submit" class="btn btn-success" onclick="export_ThongKeHoaDon(5);" >Xuất ra excel</button>			         
			         </div> 
			      </div>
			     
			 </div>
			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	    
	             <span class="scroll left-scroll"> &#171; </span>
                 <span class="scroll right-scroll" onclick="right_scroll_detail()">&#187;</span>   
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th " style="text-align: center;width:10%" >STT</th>		
		                <th class="table-th " style="text-align: center;width:10%" >MKH</th>
		                <th class="table-th " style="text-align: center;width:10%" >Tên bảng kê</th>		
		                <th class="table-th " style="text-align: center;width:10%" >Cấp 1 nhận</th>		                                
		                <th class="table-th " style="text-align: center;width:10%"  >Ngày gửi toa</th>
		                <th class="table-th " style="text-align: center;width:10%"  >Ngày nhận hàng trên toa</th>
		                <th class="table-th " style="text-align: center;width:10%"  >Số ngày gửi trễ</th>		               
		                <th class="table-th" style="text-align: center;width:10%" >Mã hàng</th>	
		                <th class="table-th" style="text-align: center;width:10%" >Tên hàng & qui cách</th>	
		                <th class="table-th" style="text-align: center;width:10%" >Số thùng</th>	
		                <th class="table-th" style="text-align: center;width:10%" >Thành tiền</th>	
		            </tr>
		         
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 10;i++){%>
			       		 <tr  >
			       		    <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			            
			                
			            </tr>
		            <%} %>
		        </tbody>
		    </table>
        </div> 	    	   
				
				
			</div>
		</div>
		
		
	    
	    </div>
	    
	   
	   
	   
	   
	   
	   
	   
	   
	   
		
		
		
		
		
		
		
		
	</div><!-- tab-content -->
	
	</div>
	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->

	  <div id="dialog" title="images" >
		
	    <img src="" alt=""  id ="images_dailog" style="max-height: 700px!important;">
	
	   </div>
	   
</div>
<!-- /page content -->



<div id="custom_notifications" class="custom-notifications dsp_none">
	<ul class="list-unstyled notifications clearfix"
		data-tabbed_notifications="notif-group">
	</ul>
	<div class="clearfix"></div>
	<div id="notif-group" class="tabbed_notifications"></div>
</div>

<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script src="js/bootstrap-datepicker.min.js"></script>
  


<!-- Datatables -->
<%-- <script src="js/datatables/js/jquery.dataTables.js"></script> --%>
<!--<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>-->

<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/datepicker.min.css"/>
<link rel="stylesheet" href="css/stylesDilog2.css"/>
<link rel="stylesheet" href="css/jquery-ui.min.css"/>

<!-- chart js -->
<script src="js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="js/icheck/icheck.min.js"></script>

<script src="js/custom.js"></script>




 <!-- daterangepicker -->
   
  
   <%--  <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
   --%>
 <script type="text/javascript">
        $(document).ready(function () {      
             
        	  $('#from_datePicker').datepicker({
  	            format: 'dd/mm/yyyy'
  	        }).on('changeDate', function(e) {
  	            // Revalidate the date field
  	            $('#eventForm').formValidation('revalidateField', 'eventDate');
  	        });		   
  	        $('#to_datePicker').datepicker({
  	            format: 'dd/mm/yyyy'
  	        }).on('changeDate', function(e) {
  	            // Revalidate the date field
  	            $('#eventForm').formValidation('revalidateField', 'eventDate');
  	        }); 
  	        
  	        var d = new Date();
  		    var curr_date = d.getDate();
  		    var curr_month = d.getMonth() + 1; //Months are zero based
  		    var curr_year = d.getFullYear();
  		    if(curr_date<10){
  		    	curr_date = "0"+curr_date;
  		    }
  		    if(curr_month<10){
  		    	curr_month = "0"+curr_month;
  		    }
  		    let fromDate = "01/" + curr_month + "/" +curr_year ;
  		  	let currentDate = curr_date + "/" + curr_month + "/" +curr_year ;
  		  
  		    $("#form_date_search").val(fromDate);
  		    $("#to_date_search").val(currentDate);
  		   
  		     $("#dialog").dialog({
                  open: function (event, ui) {
                      $element.load("/Reader/GetResults/" + id);
                  },
                  autoOpen: false,
                  autoResize: true,  
                  width: 'auto',
                  maxwidth:  $(window).width()-100,
                  minWidth: 450,
                  height: 'auto',
                  maxheight: $(window).height()-100,
                  minHeight: 450,
                  position: { my: 'top', at: 'top+50', of: window, position: 'fixed' },
               
                  title: "",
                  model: true,
                  show: "show",
                  hide: "hide",               
                  closeOnEscape: true,               
                  close: function () {
                      $(this).dialog('close');
                  }
              });  
  		     
  		
  		    $('#table_detail').DataTable({     			   
  			    "bProcessing": false,	            	    
      	    	"scrollX": true,
      	    	"fixedHeader": true,
      	    	"autoWidth": true,
      	    	"bScrollAutoCss": false, 
                 fixedColumns: true, 
          	   fixedHeader: {
  		            header: true,
  		            footer: true
  		        } ,
                  aLengthMenu: [
  					        [15,25, 50, 100,-1],
  					        [15,25, 50, 100,"All"]
  					    ],
  					iDisplayLength: 25 ,
  					 aoColumnDefs: [				 					              
  					             
  					                { aTargets: [ 9 ], bSortable: false },
  					                { aTargets: [ '_all' ], bSortable: true },
  					               ]
  				 	
  				   
  		      
             }); 
  		    
  		  getInvoiceDataFilterReport();
  		  loadStaffInvoiceReport();
  		  lookupCaptureInvoiceTypeAction();

        });
        
        function showDilogImages(id_mages){
        	
        	$("#images_dailog").attr("src",document.getElementById("id_mages_"+id_mages).innerHTML);     		
   			$("#dialog").dialog("option", "position", {
   				my: "center",
   				at: "center",
   				of: window,
   			   create: function() {
   		        $(this).css("maxHeight", $(window).height()-300);        
   		       }
   			});
   			if ($("#dialog").dialog("isOpen") == false) {
   				$("#dialog").dialog("open");
   				document.getElementById("dlg_remarks").focus();
   			} 
   	
   	 }
   	 function dlg_cancelclose(){
   		 if ($("#dialog").dialog("isOpen") == true) {
   				$("#dialog").dialog("close");
   			} 
   	 }
   	 function dlg_file_upload_cancel(){
   		 if ($("#dialog_delete").dialog("isOpen") == true) {
   				$("#dialog_delete").dialog("close");
   			} 
   	 }
   	 
   	function btnExport(){
   		
   	 var sent_late             = document.getElementById("cbb_ngaygoitre").value;
	 var customer_id           = document.getElementById("cbb_khachhang").value;
	 var user_id               = document.getElementById("cbb_nvtt").value;
	 var invoice_type          = document.getElementById("cbb_loaibangke").value;
	 var end_day               = document.getElementById("to_date_search").value;
	 var start_day             = document.getElementById("form_date_search").value;

 		location.href		   ="getInvoiceDataFilterReport1Action?sent_late="+sent_late+
							    "&customer_id="+customer_id+
							    "&user_id="+user_id+
							    "&invoice_type="+invoice_type+
							    "&end_day="+end_day+
							    "&start_day="+start_day;
	}
   	
   	function getInvoiceDataFilterReport(){   		
	   	 var sent_late             = document.getElementById("cbb_ngaygoitre").value;
		 var customer_id           = document.getElementById("cbb_khachhang").value;
		 var user_id               = document.getElementById("cbb_nvtt").value;
		 var invoice_type          = document.getElementById("cbb_loaibangke").value;
		 var end_day               = document.getElementById("to_date_search").value;
		 var start_day             = document.getElementById("form_date_search").value;
	
	   	 $.ajax({  		
	   	       type: "GET",
	              url     : "getInvoiceDataFilterReport1Action?sent_late="+sent_late+
	            		    "&customer_id="+customer_id+
	            		    "&user_id="+user_id+
	            		    "&invoice_type="+invoice_type+
	            		    "&end_day="+end_day+
	            		    "&start_day="+start_day,	          
	              data    : "",
	              success : function(responseText) {	
	           	     console.log(responseText);
	           	    var table ="";
	           	    table +="<table id='table_detail' class='table table-striped table-bordered table table-hover' cellspacing='0' width='100%'>";
	  	        	table +="<thead>";
	  	        		table +="<tr class='w3-btn'>";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Loại bảng kê</th>";
	  	        		table +="   <th class='table-th' style='text-align: center;width:20%'>Mã KH</th>	";	
	  	        		table +="   <th class='table-th' style='text-align: center;width:35%'>Tên KH</th>";
	  	        		table +="   <th class='table-th' style='text-align: center;width:35%'>KH Cấp 1</th>";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>NVTT</th>	";
	  	        		
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Ngày nhận toa</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Ngày nhận hàng</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Số ngày gởi trể</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Ghi chú</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Mã sản phẩm</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Tên sản phẩm	</th>	";
	  	        		
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Số lượng</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Số thùng</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Đơn giá</th>	";
	  	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Thành tiền</th>	";
	  	        		table +="   <th style='display: none'>Hinhanh</th>	";
	 		              
	  	        		table +=" </tr> </thead>   <tbody> ";   
	  	        		
	 		         var stt =1;
	 		         var id_mages=1;
	  	        	 for (i in responseText) {   
	  	        		var invoiceType          =responseText[i].invoice_type_id.invoiceType;
	  	        		var customer_code        =responseText[i].customer_code;
	  	        		var customer_name        =responseText[i].customer_name;
	  	        		var customer_name_level1 =responseText[i].customer_name_level1;
	  	        		var staff_name           =responseText[i].staff_name;
	  	        		
	  	        		var date_company_received =responseText[i].date_company_received;
	  	        		var date_product_received =responseText[i].date_product_received;
	  	        		var date_sent_late        =responseText[i].date_sent_late;
	  	        		var notes                 =responseText[i].notes;
	  	        		if(date_company_received!=null && date_company_received.length>10){
	  	        			date_company_received = date_company_received.substring(0,10);
	  	        		}
	  	        		if(date_product_received!=null && date_product_received.length>10){
	  	        			date_product_received = date_product_received.substring(0,10);
	  	        		}
	  	        		if(responseText[i].product_ids!=null){
	  	        			var product_ids           = responseText[i].product_ids.split("`");
		  	        		var product_names         = responseText[i].product_names.split("`");
		  	        		var quantitys             = responseText[i].quantitys.split("`");
		  	        		var total_boxs            = responseText[i].total_boxs.split("`");
		  	        		var total_prices          = responseText[i].total_prices.split("`");
		  	        		var unit_prices           = responseText[i].unit_prices.split("`");
		  	        		
		  	        		var max_size = product_ids.length -1 ;
		  	        		var lc =0;
		  	        		
		  	        		
		  	        		var imagePath_hidden ="";
		  	           	     var file_path        = responseText[i].management_id.file_path.split("/DX_Images/");
		  	           	     var file_name        = responseText[i].management_id.file_name;
		  	           	     if(file_path.length>1){
		  	           	    	imagePath_hidden = file_path[1] +"/"+file_name;
		  	           	     }
		  	           	    
		  	           	     var url = window.location.href.toString();
		  	           	     if(url.includes("localhost")){
		  	           	        imagePath_hidden = "http://nv.dongxanhvn.com:8077/DX_Images/13012019/1547367853715/z1161354633309_0916f6c1aab1f212fa463e5ce5e8d11e.jpg";
		  	           	     }else{
		  	           	    	imagePath_hidden = "http://nv.dongxanhvn.com:8077/DX_Images/"+imagePath_hidden;
		  	           	     }
		  	           	     
		  	        	    for(row in product_ids){
		  	        	    	if(lc<max_size){
		  	        	    		id_mages++;
		  	        	    		table +="  <tr id ="+id_mages+" ondblclick='showDilogImages("+id_mages+")'>";
				  	        		table +="     <td>"+invoiceType+"</td>";
				  	            	table +="     <td>"+customer_code+"</td>";
				  	        		table +="     <td>"+customer_name+"</td>";
				  	        		table +="     <td>"+customer_name_level1+"</td>";
				  	        		table +="     <td>"+staff_name+"</td>";
				  	        		
				  	        		
				  	        		table +="     <td class='right'>"+date_company_received+"</td>";
				  	            	table +="     <td class='right'>"+date_product_received+"</td>";
				  	        		table +="     <td>"+date_sent_late+"</td>";
				  	        		table +="     <td>"+notes+"</td>";
				  	        		
				  	        		
				  	        		table +="     <td>"+product_ids[row]+"</td>";
				  	        		table +="     <td>"+product_names[row]+"</td>";  
				  	        		table +="     <td class='right'>"+getDataNumber(quantitys[row])+"</td>";
				  	            	table +="     <td class='right'>"+getDataNumber(total_boxs[row])+"</td>";
				  	            	table +="     <td class='right'>"+getDataNumber(unit_prices[row])+"</td>";
				  	        		table +="     <td class='right'>"+getDataNumber(total_prices[row])+"</td>";				  	        		
				  	        		table +="     <td style='display: none' id ='id_mages_"+id_mages+"'>"+imagePath_hidden+"</td>";
				  	        		
				  	        		table +="  </tr>";
		  	        	    	}
				  	        	lc++;
		  	        	    }
		  	        		
	  	        		}
	  	        		
	  	        		
	  	        	
	  	        		stt++;
	  	        		}
	  	        	    table +="</tbody>";
	  	        	    table +="</table>";
	  	        	    
	  	        	   $('#detail_data_table').html(table);
	 	        	   
	  	        	  $('#table_detail').DataTable({     			   
	  	 			    "bProcessing": false,	            	    
	  	     	    	"scrollX": true,
	  	     	    	"fixedHeader": true,
	  	     	    	"autoWidth": true,
	  	     	    	"bScrollAutoCss": false, 
	  	                fixedColumns: true, 
	  	         	   fixedHeader: {
	  	 		            header: true,
	  	 		            footer: true
	  	 		        } ,
	  	                 aLengthMenu: [
	  	 					        [15,25, 50, 100,-1],
	  	 					        [15,25, 50, 100,"All"]
	  	 					    ],
	  	 					iDisplayLength: 25
	  	 				 	
	  	 				   
	  	 		      
	  	            }); 
	              }
	   	   });
   }
   	
   	function getDataNumber(val){
   	    try {
			val = parseFloat(val).toLocaleString();
			return val;
		} catch (e) {
			// TODO: handle exception
		}
		return "";
   	}
	function loadCusByStaffInvoiceReport(){  
		var user_id               = document.getElementById("cbb_nvtt").value;
	   	 $.ajax({  		
	   	       type: "GET",
	              url     : "loadCusByStaffInvoiceReport1Action?user_id="+user_id,	          
	              data    : "",
	              success : function(responseText) {
	           	     var cbb_nvtt = document.getElementById("cbb_khachhang");
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].businessName;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  }
	              }
	   	   });
  }
	
	
	function loadStaffInvoiceReport(){ 
	   	 $.ajax({  		
	   	       type: "GET",
	              url     : "loadStaffInvoiceReport1Action",	          
	              data    : "",
	              success : function(responseText) {
	            	  var cbb_nvtt = document.getElementById("cbb_nvtt");
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].userName;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  }
	              }
	   	   });
  }
	
	function lookupCaptureInvoiceTypeAction(){ 
	   	 $.ajax({  		
	   	       type: "GET",
	              url     : "lookupCaptureInvoiceTypeAction",	          
	              data    : "",
	              success : function(responseText) {
	            	  var cbb_nvtt = document.getElementById("cbb_loaibangke");
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
	function ThongKeHoaDon(step){
		if(step==2){
			ThongKeMatHangCapI();
		}else if(step==3){
			TongHopTheoNVTT();
		}else if(step==4){
			TongHopTheoCap2();
		}else if(step==5){
			ChiTietToaCap2();
		}
		
	}
	
	function ThongKeMatHangCapI(){
		alert("ThongKeMatHangCapI");
		 $.ajax({  		
	   	       type: "GET",
	              url     : "getReportInvoiceByCus1Action?end_day=null&start_day=null",	          
	              data    : "",
	              success : function(responseText) {		            	 
	            	  console.log("=============991============");
	            	  alert(responseText);		           	   
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].businessName;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  } 
	              }
	   	   });
	}
	
    function TongHopTheoNVTT(){
    	alert("TongHopTheoNVTT");
    	$.ajax({  		
	   	       type: "GET",
	              url     : "getReportInvoiceByStaffAction?staff_id=0&end_day=null&start_day=null",	          
	              data    : "",
	              success : function(responseText) {		            	 
	            	  console.log("=============991============");
	            	  alert(responseText);		           	   
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].businessName;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  } 
	              }
	   	   });
	}
	
	
	function TongHopTheoCap2(){
		alert("TongHopTheoCap2");
		$.ajax({  		
	   	       type: "GET",
	              url     : "loadCusByStaffInvoiceReport1Action?user_id=1",	          
	              data    : "",
	              success : function(responseText) {		            	 
	            	  console.log("=============991============");
	            	  alert(responseText);		           	   
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].businessName;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  } 
	              }
	   	   });
		
	}
    function ChiTietToaCap2(){
    	alert("ChiTietToaCap2");
    	
    	$.ajax({  		
	   	       type: "GET",
	              url     : "getReportInvoiceByStaffAction?staff_id=0&end_day=null&start_day=null",	          
	              data    : "",
	              success : function(responseText) {		            	 
	            	  console.log("=============991============");
	            	  alert(responseText);		           	   
	            	  for (i in responseText) {  	        		
		  	        		console.log(responseText[i]);
		  	        		  var option   = document.createElement("option");
			            	  option.text  = responseText[i].businessName;			            	 
			            	  option.setAttribute ("value", responseText[i].id);
			            	  cbb_nvtt.add(option);
	            	  } 
	              }
	   	   });
	}
    function export_ThongKeHoaDon(step){
    	if(step==1){
    		export_Excel();
    	}else if(step==2){
			export_thongKeMatHangCapI();
		}else if(step==3){
			export_TongHopTheoNVTT();
		}else if(step==4){
			export_TongHopTheoCap2();
		}else if(step==5){
			export_ChiTietToaCap2();
		}
    }
    function export_thongKeMatHangCapI(){ 
    	alert("export_thongKeMatHangCapI");
    }
    
    function export_TongHopTheoNVTT(){ 
    	alert("export_TongHopTheoNVTT");
    }
	 function export_TongHopTheoCap2(){ 
		 alert("export_TongHopTheoCap2");
	 }
	 function export_ChiTietToaCap2(){ 
		 alert("export_ChiTietToaCap2");
	 }
 
	function export_Excel(){ 
	   	 var sent_late             = document.getElementById("cbb_ngaygoitre").value;
		 var customer_id           = document.getElementById("cbb_khachhang").value;
		 var user_id               = document.getElementById("cbb_nvtt").value;
		 var invoice_type          = document.getElementById("cbb_loaibangke").value;
		 var end_day               = document.getElementById("to_date_search").value;
		 var start_day             = document.getElementById("form_date_search").value;
		 
		 var href           =  "exportInvoiceDataReport1Action?sent_late="+sent_late+
							    "&customer_id="+customer_id+
							    "&user_id="+user_id+
							    "&invoice_type="+invoice_type+
							    "&end_day="+end_day+
							    "&start_day="+start_day;    
	 		
	 		location.href		= href;
	   
   }
	
	


   	
   	
   	 
</script>
    
<style type="text/css">
.right{
  text-align: right;
}
.w3-btn{
    background: rgba(52, 73, 94, 0.94)!important;
    color: #ECF0F1;
}
.detail_data_table_final{
    min-height: 700px;
}
#table_detail tr {
height: 30px;
}
.mat_hang_cap_1_header_table{
   text-align: center;
   width:10% ;
  padding-bottom: 30px!important;

}


</style>

</body>

</html>