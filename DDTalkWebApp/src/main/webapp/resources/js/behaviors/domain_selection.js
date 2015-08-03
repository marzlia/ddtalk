/**
 * Created by peter on 7/29/15.
 */
DomainSelection = function(element) {
  $("select", element).selectize({
     valueField: 'description',
     create: false,
      load: function(query, callback) {
          $.ajax({
              url:'',
              type: 'GET',
              dataType: 'json',
              error: function() {
                  callback();
              },
              success: function(data) {
                callback(data);
              }
          });
      }
  });
};