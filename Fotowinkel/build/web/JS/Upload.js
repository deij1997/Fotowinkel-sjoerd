/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Catch the form submit and upload the files
  $(function() {
        $('#upload-form').ajaxForm({
success: function( response)
        {
            if(typeof response === 'undefined')
            {
                // Success so call function to process the form
               // submitForm(event, data);
                console.log(response);
                               console.log('ERRORS: ' + response);

            }
            else
            {
                // Handle errors here
                console.log('ERRORS: ' + response);
            }
        },
        error: function(response)
        {
            // Handle errors here
            console.log('ERRORS: ' + response);
        }
        });
    });
    