<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ include file="header.jsp"%> 
<%@ include file="user_profile.jsp"%> 
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">

				 <form  action="ExportDeatail" method="post"  enctype="multipart/form-data"class="form-horizontal form_search"> 
			     <!--  <div  class="form-horizontal form_search"> -->
			      <div class="form-group">			      
			       <div class="col-md-2 form_title" >Tên đường dẫn </div>
				   <div class="col-md-5 input_upload" >
				        <input type="file" id="fileUpload" name="fileList[]" webkitRelativePath  multiple /> 
				    </div>
				    <div class="col-md-2" >  <button type="button" class="btn btn-success" onclick="upload();">Upload</button>			  </div>
			      </div>
			      
			      <div class="form-group" style="height:200px ;width:95%; overflow-y: scroll;padding-left:5%">	
			      
			  
		             
		        <table id="table_list_file" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:10%">STT</th>		
		                <th class="table-th" style="text-align: center;width:75%">Tên Hình</th>
		                <th class="table-th" style="text-align: center;width:10%">Dung Lượng</th>	
		              
		            </tr>
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 0 ;i++){%>
			       		 <tr>
			       		    <td></td>
			                <td></td>
			                <td></td>
			               
			                
			            </tr>
		            <%} %>
		        </tbody>
		    </table>
		    
			      </div>
			 </form>

			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	    
	             <span class="scroll left-scroll"> &#171; </span>
                 <span class="scroll right-scroll" onclick="right_scroll_detail()">&#187;</span>   
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:10%">STT</th>		
		                <th class="table-th" style="text-align: center;width:75%">Tên Hình</th>
		                <th class="table-th" style="text-align: center;width:10%">Ngày tạo</th>	
		              
		            </tr>
		        </thead>        
		        <tbody>  
		           <% for(int i=0 ;i< 100 ;i++){%>
			       		 <tr>
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
	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->	   
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
<link rel="stylesheet" href="css/hoadon_themmoi.css"/>


<!-- Datatables -->
<%-- <script src="js/datatables/js/jquery.dataTables.js"></script> --%>
<!--<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>-->

<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/datepicker.min.css"/>

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
  
 <script type="text/javascript">
 
 document.getElementById("fileUpload").addEventListener("change", function(event) {
	  let output = document.getElementById("listing");
	  let files = event.target.files;
	  var tmppath = URL.createObjectURL(event.target.files[0]);
	 alert(files[0].mozFullPath);
	
	 alert( document.getElementById("fileUpload").val);
	  for (var i=0; i<files.length; i++) {
		  var stt  = i+1;
		  var size = files[i].size ;
		  var dungluong ="";
		  size = size /1024;
		  if(1024<size){
			  size = size /1024;
			  size = size.toFixed(2);
			  dungluong = size +"MB";
		  }else{
			  size = size.toFixed(2);
			 dungluong = size+"KB"; 
		  }
		 
		  $('#table_list_file').append('<tr><td>'+stt+'</td><td>'+files[i].name+'</td><td>'+dungluong+'</td></tr>');	 
	  };
	}, false);
 
        $(document).ready(function () {  		
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

        });
        
  
   	 
</script>
    


</body>

</html>