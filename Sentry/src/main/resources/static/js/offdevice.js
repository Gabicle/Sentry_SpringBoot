function offdevice(){
	 $.ajax({
	    url: "offDevice",
	    success: function (data){
	        location.reload();
            console.log("success");
       		},
	       error: function (e){
	         console.log(e);
	      }
		})
      
}