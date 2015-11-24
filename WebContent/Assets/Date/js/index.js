$(function(){
     $(document).ready(function() {
        alert("loaded");
        $('#startdate').datepicker({
             'format': 'dd/mm/yyyy'
         }).on('changeDate', function(e){
             $('#startdate').datepicker('hide');
         });
         
    });
});
