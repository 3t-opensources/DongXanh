<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thêm mới</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/jquery.iviewer.css"/>
<link rel="stylesheet" href="css/pretty-split-pane.css"/>
<link rel="stylesheet" href="css/split-pane.css"/>  
</head>
<body>
<div class="navbar nav_title" style="border: 0;">
	<a href="homeAction" class="site_title">
		<img src="./images/logo_dongxanh.png" alt="ĐỒNG XANH" height="50" width="50">
		<span>ĐỒNG XANH</span></a>
</div>
<div class="clearfix"></div>
<div class="right_col" role="main">
		<div class="pretty-split-pane-frame" id = "svr-content-height">
			<div class="split-pane vertical-percent">
				<div class="split-pane-component" id="left-component">
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
				<div class="split-pane-divider" id="my-divider"></div>
				<div class="split-pane-component" id="right-component">
					<div class="pretty-split-pane-component-inner">
						
						<form name = "form-field" id = "form-text-field" action = "saveform" method = "post" onsubmit = "return confirmwhensubmit()">
						<div id="wrapper" style="width : 100%; height : 100%; float: right">
							<!-- <div class="panel-heading" style="color: blue;font-weight: bold;">META DATA </div> -->
									<div class="metadata" style="z-index: 1">									    
									        <div class="row " >
	                                       		  <div class="col-lg-3" ><span>Loại bảng kê  </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "loaibangke" name="loaibangke" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	 
	                                       		  
	                                       		  <div class="col-lg-3" ><span>Tên bảng kê  </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "tenbangke" name="tenbangke" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                        		  
	                                        </div>
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-3" ><span>Mã KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "maKH" name="maKH" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-3" ><span>Tên KH </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "tenKH" name="tenKH" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                       		  
	                                        </div>
										     <div class="clear"></div>										     
										      <div class="row " >
	                                       		  <div class="col-lg-3" ><span>KH Cấp 1 </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "KHcap1" name="KHcap1" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-3" ><span>NVTT </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "NVT" name="NVTT" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                                 		  
	                                        </div>
										     <div class="clear"></div>	
										      <div class="row " >
	                                       		  <div class="col-lg-3" ><span>Ngày nhận toa </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "ngaynhantoa" name="ngaynhantoa" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-3" ><span>Ngày nhận hàng </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "ngaynhanhang" name="ngaynhanhang" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                          		  
	                                        </div>
	                                        
	                                        <div class="clear"></div>	
										      <div class="row " >
	                                       		  <div class="col-lg-3" ><span>Số ngày gởi trể</span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "kreditor_name" name="kreditor_name" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	   
	                                       		  
	                                       		 <div class="col-lg-3" ><span>Ghi chú </span>  </div>	                                       		  
	                                       		  <div class="col-lg-3">			                                       									
				                                          <input value = "" id = "kreditor_name" name="kreditor_name" class="form-control" ondblclick = "getIdTag(this)" tabindex="1"/>				                                         
	                                       		  </div>	                                          		  
	                                        </div>
										     <div class="clear"></div>
										     
	                              </div> 	                                      
				            <!-- /.row -->
				            <div class="clear"></div>
						    <div class="row">
							      <div class=position_table>							       
							        <table class="table table-fixed table-bordered table-inverse "  id="datatable-debitor">
							          <thead>
							            <tr>										            
							             <th class = "header-table-column-debitor table-th">STT</th>
							             <th class = "header-table-column-debitor table-th">Số lượng</th>
							             <th class = "header-table-column-debitor table-th">Loại hàng</th>
							             <th class = "header-table-column-debitor table-th">Đơn giá</th>
							             <th class = "header-table-column-debitor table-th">Thành tiền</th>
							             <th class = "header-table-column-debitor table-th">Ghi chú </th>
							             <th class = "header-table-column-debitor table-th"></th>
							            </tr>
							          </thead>
							          <tbody>
							          <% for(int i = 1; i < 10; i++) {%>
		                                <tr class="odd gradeX">
		                                    <td><%=i %>		</td>
											<td>
												<input value = "" id = "<%=i %>" name="r<%=i%>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "<%=i %>" name="r<%=i%>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "<%=i %>" name="r<%=i%>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "<%=i %>" name="r<%=i%>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "<%=i %>" name="r<%=i%>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											<td>
												<input value = "" id = "<%=i %>" name="r<%=i%>" class="custom-input-debitor form-control" ondblclick = "getIdTag(this)" tabindex=""/>
											</td>
											
										</tr>
										<% } %>						
							          </tbody>
							        </table>
							      </div>
							  </div>   
				            <div class="well"><p align="center">
				                 <button type="button" class="btn btn-success" onclick="searchDetail();">Xóa toa/bảng kê</button>			  
				            	 <button type="button" class="btn btn-success" onclick="searchDetail();">Lưu toa/bảng kê</button>			  
                      		</div>
                      		
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->
</div>
<!-- /page content -->


 <!-- VIEW IMAGE -->
     <script type="text/javascript" src="/js/jquery.mousewheel.min.js" ></script>
    <script type="text/javascript" src="/js/jquery.iviewer.js" ></script> 
    <script type="text/javascript">       		
       		<%-- var imagePath = "<%=fullFilePath%>"; --%>
       		var imagePath = "";
      
            $(document).ready(function(){
            	alert("truong xuan");
            	$("#svr-height").height(window.innerHeight);
    			$("#svr-content-height").height(window.innerHeight);
    		    $("#viewer").height(document.getElementById("left-component").clientHeight);
    		    
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
    </script>
    <script type="text/javascript">
    	$(function(){
    		
   			$("#viewer").jScrollPane();
   		});
    </script>
    
    
    <!-- SPLIT PANE -->
	<script src="/resources/js/split-pane.js"></script>


</body>
</html>