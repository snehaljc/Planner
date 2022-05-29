function toggleLoginSignup() {
    const login = document.getElementById('login');
    const loginState = login.style.display;
    const register = document.getElementById('register');
    console.log("Im on bitvh");
    if (loginState == 'block') {
        login.style.display = 'none';
        register.style.display = 'block';
    } else {
        register.style.display = 'none';
        login.style.display = 'block';
    }
}

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
     });

     $("#addedusertable").on('click', '#removeuser', function () {
        $(this).closest('tr').remove();
    });

    if(InvitedUsers.length != 0) {
        $('#nouserlistmsg').hide();
    }
  })

  