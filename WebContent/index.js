function toggleLoginSignup() {
    const login = document.getElementById('login');
    const loginState = login.style.display;
    const register = document.getElementById('register');

    if (loginState == 'block') {
        login.style.display = 'none';
        register.style.display = 'block';
    } else {
        register.style.display = 'none';
        login.style.display = 'block';
    }
}
let dateid = document.getElementsByName("eventdate")[0];
if(dateid) document.getElementsByName("eventdate")[0].min = new Date().toISOString().slice(0, 16);

$('#flexdatalist').flexdatalist({
    minLength: 1,
});

let InvitedUsers = [];

$('document').ready(function() {
    //If user is not selected in search list then add button will be disabled
    $('.invite_user_input').on('keyup', function() {
        let empty = false;
        empty = $(this).val().length == 0;
    
        if (empty)
          $('.add_user').attr('disabled', 'disabled');
        else
          $('.add_user').attr('disabled', false);
      });
    $('.add_user').click(function() {
        $("#addedusertable").append('<tr><td>'+$('#eventinvite').val()+'</td><td><button class="btn add_user" type="button" id="removeuser" ><i class="bi bi-trash3"></i></button></td></tr>');
        let userlist = $("#invitationlist").val() ? $("#invitationlist").val() +','+ $('#eventinvite').val() : $('#eventinvite').val();
        $('#invitationlist').attr('value', userlist);
     });

     $("#addedusertable").on('click', '#removeuser', function () {
        $(this).closest('tr').remove();
    });

    if(InvitedUsers.length != 0) {
        $('#nouserlistmsg').hide();
    }
  })


 $('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var eventid = button.data('eventid'); // Extract info from data-* attributes
   $('a.action').attr("href","dashboard?action=delete&id="+eventid);
  });
  
  $('#eventimage').on('change',function(){
                //get the file name
                var fileName = $(this).val();
                //replace the "Choose a file" label
                $(this).next('.custom-file-label').html(fileName);
            });
            
  
  function clearToast() {
    $.ajax({
        url: '/Planner/api?action=cleartoast',
        type: "GET", 
        error : function(){ 
            console.log('Error in the AJAX call of clearing toast'); 
        },
        success: function(msg){      
                console.log(msg);
        }
})
}

function clearToastSuccess() {
    $.ajax({
        url: '/Planner/api?action=cleartoastsuccess',
        type: "GET", 
        error : function(){ 
            console.log('Error in the AJAX call of clearing toast'); 
        },
        success: function(msg){      
                console.log(msg);
        }
})
}

function updateNearByEvents(item) {
	let value = item.value;
    $.ajax({
        url: '/Planner/dashboard?action=nearby&lid='+value,
        type: "GET", 
        error : function(){ 
            console.log('Error in the AJAX call of clearing toast'); 
        },
        success: function(msg){      
                console.log(msg);
        }
})
}


 