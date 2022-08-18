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
	methods: {
		editKorisnik : function() {
    		this.backup = [this.korisnik.username, this.korisnik.ime, this.korisnik.prezime, this.korisnik.pol, this.korisnik.datumRodjenja, this.korisnik.password];
    		this.mode = 'EDIT';
    	},
    	updateKorisnik : function(korisnik) {
    		axios
    		.post("rest/register/updatejson", korisnik)
    		.then(response => toast('Korisnik ' + korisnik.ime + " " + korisnik.prezime + " uspešno snimljen."));
    		this.mode = 'BROWSE';
    	},
    	cancelEditing : function() {
    		this.korisnik.username = this.backup[0];
    		this.korisnik.ime = this.backup[1];
    		this.korisnik.prezime = this.backup[2];
    		this.korisnik.pol = this.backup[3];
    		this.korisnik.datumRodjenja = this.backup[4];
    		this.korisnik.password = this.backup[5];
    		this.mode = 'BROWSE';
    	}
	}
	
	
	
	
	
	
});

/*$(document).ready(function() {
	
	windows.alert("poruka");
	
	window.alert(korisnik);

});*/