var profilKorisnika = new Vue({
	el:'#korisnickeInformacije',
	data: {
		korisnik: null,
		mode: "BROWSE"
	},
	mounted () {
        axios
          .get('rest/login/currentUser')
          .then(response => (this.korisnik = response.data))
    },
	
	
	
	
	
	
	
});