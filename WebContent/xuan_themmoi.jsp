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

				 <form  action="ExportDeatail" method="post" onsubmit="return checksearchDate()" class="form-horizontal form_search"> 
			     <!--  <div  class="form-horizontal form_search"> -->
			      <div class="form-group">			      
			       <div class="col-md-1 form_title" >Tên đường dẫn </div>
				   <div class="col-md-5 input_upload" >
				        <input type="file" id="fileUpload" name="fileList" webkitdirectory multiple /> 
				    </div>
				    <div class="col-md-2" >  <button type="button" class="btn btn-success" onclick="upload();">Upload</button>			  </div>
			      </div>
			      
			      <div class="form-group" style="height:300px ; overflow-y: scroll;">	
			     <!--  <ul id="listing"></ul>	 -->
			      <table id="list_file" class="table_list_file detail_data_table" cellspacing="0">
		           <thead>
		            <tr class="w3-btn">
		                <th class="table-th" style="text-align: center;width:11%">STT</th>		
		                <th class="table-th" style="text-align: center;width:11%">Tên File</th>
		             </tr>
		            </thead>
		             <tbody> 
		              <tr> <td><td></tr> 
		              <tr> <td><td></tr> 
		              <tr> <td><td></tr> 
		              <tr> <td><td></tr> 
		              <tr> <td><td></tr> 
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
		           <% for(int i=0 ;i< 100 ;i++){%>
			       		 <tr ondblclick='showDilogImages("+<%=i %>+")' >
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
	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->

	  <div id="dialog" title="images" >
		
	    <img src="./images/img_girl.jpg" alt=""  >
	
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
  

<style>
.table_list_file{
 width: 50%;
 margin-left: 50px;
}
.view_pro {
	margin: 0px;
	text-align: left;
	padding: 10px 10px 10px 10px;
	border-style: outset;
}

th {
	text-align: center;
	vertical-align: middle;
}

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
    padding-left: 2%;
    padding-top: 10px;
    width: 8%;
}

.title_width_8{
	float: left;
    font-size: 13px;
    font-weight: bold;
    padding-left: 3%;
    padding-top: 10px;
    width: 12%;
}

.title_width_9{
	float: left;
    font-size: 11px;
    font-weight: bold;
    
    padding-top: 10px;
    width: 9%;
}

.value_width_10{
	float: left;
    font-size: 11px;
    height: 33px;
    width: 10%;
}
.value_width_170{
	float: left;
    font-size: 11px;
    height: 33px;
    width: 9%;
}
.button_width_12{
	float: left;
    font-size: 13px;
    height: 27px;
    width: 10%;
   
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
    .readwarning{
    color: red!important;
    background-color: powderblue!important;
    }
  
 
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
 td{
   height: 30px;
  /*  position:relative; */
 }
th {
  overflow: hidden; 
  text-overflow: ellipsis;
  white-space: nowrap;
}
tr{
  overflow: hidden; 
  text-overflow: ellipsis;
  white-space: nowrap;
 
}
 
 .th_table_15{
	 text-align: center;
	 width:11% ;
	 min-width: 50px ;
	 padding-left: 0px;
	 padding-right: 0px;
 }
 .cbb_search{
    width: 100%;
    height: 30px;
 }
 td{
 padding-top: 0px!important;
 padding-bottom: 0px!important;
 height: 20px!important;
 font-size: 12px;
 }


table.dataTable,
table.dataTable th,
table.dataTable td {
  -webkit-box-sizing: content-box;
  -moz-box-sizing: content-box;
  box-sizing: content-box;
}
.dataTables_scroll
{
    overflow:auto;
}
.btn-success{
	width: 120px;
	margin-left: 10px;
}

.form_search ,.detail_data_table_final {
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 4px;
    clear: both;
    margin-top: 10px;
    padding-top: 10px;
}

.form_title{
margin-left: 20px;
font-weight: bold;
padding-top: 5px;
}
.input_upload {
    background-color: #fff;
    background-repeat: repeat-x;
    border: 1px solid #becfe6;
    border-radius: 4px;
    padding-left: 0px;
   
   
}

</style>

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
 
 document.getElementById("fileUpload").addEventListener("change", function(event) {
	  let output = document.getElementById("listing");
	  let files = event.target.files;
	  var tmppath = URL.createObjectURL(event.target.files[0]);
	 
	  for (var i=0; i<files.length; i++) {
	    let item = document.createElement("li");
	  
	    item.innerHTML = files[i].name;
	    output.appendChild(item);
	  };
	}, false);
 
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
  		    currentDate = curr_date + "/" + curr_month + "/" +curr_year ;		   
  		  
  		    $("#form_date_search").val(currentDate);
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

        });
        
        function showDilogImages(stt){
			
   		 var i=0;
   		  $('#tr_'+stt).find('td').each(function(){
   			 if(i==2){				
   						
   			 }
   			 i++;
   			});
   		  
   		
   			$("#dialog").dialog("option", "position", {
   				my: "center",
   				at: "center",
   				of: window
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
   	 
</script>
    


</body>

</html>