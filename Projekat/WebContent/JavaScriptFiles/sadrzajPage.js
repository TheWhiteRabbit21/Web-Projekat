var sadrzajPage = new Vue({
    el:'#sadrzajInformacije',
    data: {
        sadrzaj: null,
        mode: "BROWSE"
    },
    mounted(){
        axios
          .get('rest/getSadrzajZaPrikazati')
          .then(response => (this.sadrzaj = response.data))
    },
    methods: {
        editSadrzaj : function(){
            this.backup = [this.sadrzaj.naziv, this.sadrzaj.tip, this.sadrzaj.trajanje, this.sadrzaj.opis, this.sadrzaj.slika, this.sadrzaj.cena];
            this.mode = 'EDIT';
        },
        updateSadrzaj : function(sadrzaj){
            axios
            .post("rest/updateSadrzaj", sadrzaj)
    		.then(response => toast('Sadrzaj ' + sadrzaj.naziv + " uspešno snimljen."));
    		this.mode = 'BROWSE';
        },
        cancelEditing : function(){
            this.sadrzaj.naziv = this.backup[0];
            this.sadrzaj.tip = this.backup[1];
            this.sadrzaj.trajanje = this.backup[2];
            this.sadrzaj.opis = this.backup[3];
            this.sadrzaj.slika = this.backup[4];
            this.sadrzaj.cena = this.backup[5];
        }

    }



});