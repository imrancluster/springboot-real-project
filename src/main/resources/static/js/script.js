$(document).ready(function(){
    console.log("Custom JS Loaded");
    
    $("a.btn-warning").click(function() {
    	console.log("Clicked");
    	
    	return false;
    });
});