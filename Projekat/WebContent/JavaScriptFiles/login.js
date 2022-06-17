$(document).ready(function() {
	$('#forma').submit(function(event) {
		event.preventDefault();
		let name = $('input[name="username"]').val();
		let price = $('input[name="password"]').val();
		$('#error').hide();
		$.post({
			url: 'rest/products',
			data: JSON.stringify({username: username, password: password}),
			contentType: 'application/json',
			success: function(product) {
				$('#success').text('Novi proizvod uspešno kreiran.');
				$("#success").show().delay(3000).fadeOut();
			},
			error: function(message) {
				$('#error').text(message);
				$("#error").show().delay(3000).fadeOut();
			}
		});
	});
});


/*$(document).ready(function() {
	$('#forma').submit(function(event) {
		event.preventDefault();
		let name = $('input[name="username"]').val();
		let price = $('input[name="password"]').val();
		$('#error').hide();
		$.post({
			url: 'rest/products',
			data: JSON.stringify({username: username, password: password}),
			contentType: 'application/json',
			success: function(product) {
				$('#success').text('Novi proizvod uspešno kreiran.');
				$("#success").show().delay(3000).fadeOut();
			},
			error: function(message) {
				$('#error').text(message);
				$("#error").show().delay(3000).fadeOut();
			}
		});
	});
});
*/




