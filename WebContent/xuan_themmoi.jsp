<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ include file="header.jsp"%> 
<%@ include file="user_profile.jsp"%> 
<%@ include file="menu.jsp"%>

<script type="text/javascript" src="js/docs-js-jquery-1.7.1.min.js"></script>	
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src = "js/es6-promise.auto.js"></script>
<link rel="stylesheet" href="css/hoadon_themmoi.css"/>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">

				 <form  action="saveData" method="post"  enctype="multipart/form-data"class="form-horizontal form_search" id ="saveImages"> 
			     <!--  <div  class="form-horizontal form_search"> -->
			      <div class="form-group">			      
			       <div class="col-md-2 form_title" >Tên đường dẫn </div>
				   <div class="col-md-5 input_upload" >
				        <input type="file" id="fileUpload" name="fileList" webkitdirectory multiple  multiple  /> 
				    </div>
				    <div class="col-md-2" >  <button type="button" class="btn btn-success" onclick="uploadImages();">Upload</button>			  </div>
			      </div>
			      
			      <div class="form-group" style="height:200px ;width:95%; overflow-y: scroll;padding-left:5%">	
				        <table id="table_list_file" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
				        <thead>
				            <tr class="w3-btn">
				                <th class="table-th" style="text-align: center;width:10%">STT</th>		
				                <th class="table-th" style="text-align: center;width:20%">Hình</th>		
				                <th class="table-th" style="text-align: center;width:45%">Tên Hình</th>
				                <th class="table-th" style="text-align: center;width:10%">Dung Lượng</th>
				            </tr>
				        </thead>        
				        <tbody> 
					       		<!--  <tr>
					       		    <td></td>
					                <td></td>
					                <td></td>
					                <td></td>
					            </tr>		         -->
				        </tbody>
				   	   </table>
		    
			      </div>
			 </form>
             <div class="tieude"> Danh sách hình chưa nhập</div>
			  <div class="detail_data_table detail_data_table_final" id ="detail_data_table">	
			    <table id="table_detail" class="table table-striped table-bordered table table-hover" cellspacing="0" width="100%">
		        <thead>
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:10%">STT</th>
		                <th class="table-th" style="text-align: center;width:20%">Hình</th>				                
		                <th class="table-th" style="text-align: center;width:45%">Tên Hình</th>
		                <th class="table-th" style="text-align: center;width:10%">User Import</th>	
		                <th class="table-th" style="text-align: center;width:10%">Ngày tạo</th>	
		              
		            </tr>
		        </thead>        
		        <tbody> 
		       		 <tr>
		       		    <td></td>
		                <td></td>
		                <td></td>
		                <td></td>	
		                <td></td>		                
		            </tr>		            
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


<div id="loading" class="se-pre-con">
       <p><img src="./images/Preloader_5.gif" /> Please Wait</p>
  </div>
<div id="custom_notifications" class="custom-notifications dsp_none">
	<ul class="list-unstyled notifications clearfix"
		data-tabbed_notifications="notif-group">
	</ul>
	<div class="clearfix"></div>
	<div id="notif-group" class="tabbed_notifications"></div>
</div>



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
<script type="text/javascript" src="js/jquery.isloading.js"></script>

<script src="js/custom.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/async/2.6.1/async.js"></script>
<%-- <script src="https://cdnjs.cloudflare.com/ajax/libs/async/2.6.1/async.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/async/2.6.1/async.min.map"></script> --%>




 <!-- daterangepicker -->
 


 <script type="text/javascript">
 	function wait(ms) {
	   return new Promise(r => setTimeout(r, ms))  
	}

	
 
	function addrow(i,file){
    	 setTimeout(function() { 
    		  var stt  = i+1;
    		  var size = file.size ;
    		  var dungluong ="";
    		  size = size /1024;
    		  if(size> 1024){
    			  size = size /1024;
    			  size = size.toFixed(2);
    			  dungluong = size +"MB";
    		  }else{
    			  size = size.toFixed(2);
    			 dungluong = size+"KB"; 
    		  }
    		
    		   var time_id   = new Date().getTime();
    		  // console.log(time_id);
    		   var time_img  = "img_"+time_id;
   			   var reader = new FileReader();    			  
   			   $('#table_list_file').append("<tr id='"+time_id+"'><td>"+stt+"</td><td> <img id='"+time_img+"' height='100' width='100'/></td><td id ='name_"+time_id+"'>"+file.webkitRelativePath+"</td><td>"+dungluong+"</td></tr>"); 
   			   reader.onload = function() {    			  		
   		    	      var dataURL = reader.result;
   		    	      var output  = document.getElementById(time_img);
   		    	      output.src  = dataURL;
   			  };  
   			  reader.readAsDataURL(file);
   			 
    	 }, 0);
		
	  
    }
	
	
	document.getElementById("fileUpload").addEventListener("change", async function(event) {
		   var stt   = 0; 
		   $.isLoading({ text: ""});		
		   $('#table_list_file > tbody  > tr').each(function() {		 
			   stt++;
		   });
		   let files = event.target.files;		
		   for (var i=0; i<files.length; i++) {	
			   if(files[i].name.toLowerCase().includes(".jpg") || files[i].name.toLowerCase().includes(".png")){
				   addrow(stt,files[i]);
				   stt++;
				   await wait(500);
			   }
			   if(i==(files.length-1)){
				   $.isLoading( "hide" );
			   }
			  
		   };
		}, false);
	
	async function main(event){
	
		   let files = event.target.files;		
		   for (var i=0; i<files.length; i++) {
			   addrow(i,files[i]);
			  // console.log("a"+i);
			   await wait(2016);
		   };
	}
	
 
	
	async  function uploadImages(){	
	   
	   var table = document.getElementById("table_list_file");
	   var sum   = 0; 
	   var stt   = 0; 
	   $('#table_list_file > tbody  > tr').each(function() {		 
		   sum++;
	   });
	   $('#table_list_file > tbody  > tr').each(function() {		 
		   stt ++;
		  // console.log("name_"+this.getAttribute("id"));
		  // console.log($("#name_"+this.getAttribute("id")).html());
		   var id_tr = this.getAttribute("id");
		   $.ajax({
			   async: false,
	           dataType: 'json',
	           contentType: "application/json",
		       type: "POST",
	           url : 'importImages1CaptureAction',	          
	           data: JSON.stringify({
			 		jobs:[ 
			 	         {  file_name: $("#name_"+id_tr).html(), 
			 	        	base64: document.getElementById("img_"+id_tr).getAttribute("src"), 
			 	        	user_name: document.getElementById("profile_username").textContent
			 	        	} 
			 	         ] 
			   	}) ,	           
	           success : function(responseText) {	   
	        	   if(responseText.statusError==1){
	        		  alert(responseText.message );
	        	   }else{
	        		   $("#"+id_tr).remove(); 
	        	   }
	        	   
	        	   // load lai table 
	        	   if(stt==sum){
	        		   LoadTableDetail();
	        		   alert("Inport thanh cong!");
	        	   }
	        	  
	           }
		   });
	   });
   }
 
        $(document).ready(function () {  
        	LoadTableDetail();
        	 
        });
        function LoadTableDetail(){
        	
        	var table="";
       	 $.ajax({  		
 		       type: "GET",
 	           url : 'getPendingJobsCaptureAction',	          
 	           data: "",
 	           success : function(responseText) {	
 	        	
 	        	table +="<table id='table_detail' class='table table-striped table-bordered table table-hover' cellspacing='0' width='100%'>";
 	        	table +="<thead>";
 	        		table +="<tr class='w3-btn'>";
 	        		table +="   <th class='table-th' style='text-align: center;width:10%'>STT</th>";
 	        		table +="   <th class='table-th' style='text-align: center;width:20%'>Hình</th>	";			                
 	        		table +="   <th class='table-th' style='text-align: center;width:35%'>Tên Hình</th>";
 	        		table +="   <th class='table-th' style='text-align: center;width:10%'>User import</th>	";
 	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Ngày tạo</th>	";
 	        		table +="   <th class='table-th' style='text-align: center;width:10%'>Delete</th>	"; 
 	        		table +=" </tr> </thead>   <tbody> ";  
 	        		
		         var stt =1;
 	        	 for (i in responseText) {  	        		
 	        		console.log(responseText[i]);
 	        		var created_time ="";
 	        		if(responseText[i].created_time!=null && responseText[i].created_time!='null'){
 	        			created_time = responseText[i].created_time;
 	        		}
 	        		table +="  <tr>";
 	        		table +="     <td>"+stt+"</td>";
 	            	table +="     <td>"+responseText[i].file_path+"</td>";
 	        		table +="     <td>"+responseText[i].file_name+"</td>";
 	        		table +="     <td>"+responseText[i].created_by+"</td>";
 	        		table +="     <td>"+created_time+"</td>"; 
 	        		table +="     <td><button type='button' class='btn btn-success' onclick='DeleteRow("+responseText[i].id+")'> Delete </button></td>";   
 	        		table +="  </tr>";
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
        
  function DeleteRow(id){
	  
	  var result = confirm("bạn có chắt chắn chắn xóa hóa đơn này?");
		if (result) {
					 $.ajax({  		
			       type: "GET",
		          url     : 'deleteListJobCaptureAction?ids='+ id,	          
		          data    : "",
		          success : function(responseText) {	
		        	  console.log(responseText)
		        	  alert("Xóa bảng kê thành công!");
		        	  var url = window.location.href.toString();			        	
		      		  window.location.replace(url);
		          }
			   });

		}
	
  }
   	 
</script>
    
<style type="text/css">
.display{
	display: none!important;
	
}

.table-th {
    background-color: #5cb85c !important;
    border-color: #4cae4c !important;
    color: #fff !important;
    font-size: 15px;
}
#detail_data_table{
  padding: 10px;
  min-height: 500px;
}
.tieude {
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
  /*   border-radius: 4px; */
    clear: both;
    margin-top: 10px;
    padding-top: 10px;
    font-size: 20px;
    font-weight: bold;
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
         background-image: url("./images/loading.gif")!important;
   	background-repeat: no-repeat!important;
   	background-attachment: fixed;
    background-position: center; 
    border-radius:7px;
    width: 200px;
    height: 200px; 
  
      
    }
    
</style>

</body>

</html>