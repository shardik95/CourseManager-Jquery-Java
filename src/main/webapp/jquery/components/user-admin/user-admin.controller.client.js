(function (){

    jQuery(main);


    function main(){
        jQuery("h1#title").css('color','red').html('User-Administration');

        var tr=$("#template");
        var tbody=$("tbody");

        var users=[
            {username:'bob'},
            {username:'charlie'}
        ];

        for(i=0;i<users.length;i++){
            var user=users[i];
            var tr1=tr.clone();
            tr1.find(".username").html(user.username);
            tbody.append(tr1);
        }

    }


})();
