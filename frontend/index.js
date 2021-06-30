$(document).ready(function () {  
    $("#txtCountry").autocomplete({  
        source: function (request, response) {  
            $.ajax({  
                url: "http://localhost:8080/search",  
                method: "post",  
                contentType: "application/json;charset=utf-8",  
                data: JSON.stringify({ term: request.term }),  
                dataType: 'json',  
                success: function (data) {  
                    response(data.d);  
                },  
                error: function (err) {  
                    alert(err);  
                }  
            });  
            console.log(request);
        }  
    });  
    
    $.ajax({
        url: 'http://localhost:8080/global',
        dataType: 'json',
        success: function(data) {
            console.log(data);
        }
    });

    
});


       