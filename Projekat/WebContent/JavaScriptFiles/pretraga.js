function addSportskiObjekatTr(sportskiObjekat) {
    let tr = $('<tr></tr>');
    let tdNaziv = $('<td>' + sportskiObjekat.naziv + '</td>');
    let tdTipObjekta = $('<td>' + sportskiObjekat.tipObjekta + '</td>');
    let tdSadrzaj = $('<td>' + sportskiObjekat.sadrzaj + '</td>');
    let tdStatus = $('<td>' + sportskiObjekat.status + '</td>');
    let tdMapa = $('<td>' + sportskiObjekat.mapa + '</td>');
    let tdLogo = $('<td>' + sportskiObjekat.logo + '</td>');
    let tdProsecnaOcena = $('<td>' + sportskiObjekat.prosecnaOcena + '</td>');
    let tdRadnoVreme = $('<td>' + sportskiObjekat.radnoVreme + '</td>');

    tr.append(tdNaziv).append(tdTipObjekta).append(tdSadrzaj).append(tdStatus).append(tdMapa).append(tdLogo).append(tdProsecnaOcena).append(tdRadnoVreme);
    $('#tabelaSportskihObjekata tbody').append(tr);
}

function addTipOption(tip){
		
    let option = $('<option value="' + tip + '">' + tip +'</option>');
    $('#filterTip').append(option);

}

$(document).ready(function () {
    /*$.get({
        //url: 'rest/products',
        url: 'rest/sportskiObjekti',
        success: function(sportskiObjekti) {
            for (let sportskiObjekat of sportskiObjekti) {
                addSportskiObjekatTr(sportskiObjekat);
            }
        }
    });*/

    $.get({
		url: 'rest/filterTipovi',
		success: function(tipovi){
			//ovde dodati prvi red za value=0
			$('#menadzer').append('<option value="0"></option>');
			for(let tip of tipovi){
					addTipOption(tip);
			}
		}
	})

    $('#filterByTip').submit((event) => {

        event.preventDefault();
        let tip = $('#filterTip').val();
        let url = 'rest/filter/sportskiObjekat/tip/' + tip;

        $.get({
            url: url,
            contentType: 'application/json',
            success: function (sportskiObjekti) {
                $("#tabelaSportskihObjekata tbody").html("");
                console.log(sportskiObjekti);
                for (let sportskiObjekat of sportskiObjekti) {
                    addSportskiObjekatTr(sportskiObjekat);
                }
                $('#naziv').val('');
                $('#tipObjekta').val('');
            },
        })
    });

    $('#filterByOtvorenost').submit((event) => {

        event.preventDefault();
        let url = 'rest/filter/sportskiObjekat/otvoreni';

        $.get({
            url: url,
            contentType: 'application/json',
            success: function (sportskiObjekti) {
                $("#tabelaSportskihObjekata tbody").html("");
                console.log(sportskiObjekti);
                for (let sportskiObjekat of sportskiObjekti) {
                    addSportskiObjekatTr(sportskiObjekat);
                }
                $('#naziv').val('');
                $('#tipObjekta').val('');
            },
        })
    });

    $('#sortByName').submit((event) => {

        event.preventDefault();
        let direction = $('#sortName').val();
        let url = 'rest/sort/sportskiObjekat/naziv/' + direction;

        $.get({
            url: url,
            contentType: 'application/json',
            success: function (sportskiObjekti) {
                $("#tabelaSportskihObjekata tbody").html("");
                console.log(sportskiObjekti);
                for (let sportskiObjekat of sportskiObjekti) {
                    addSportskiObjekatTr(sportskiObjekat);
                }
                $('#naziv').val('');
                $('#tipObjekta').val('');
            },
        })
    });

    $('#sortByLocation').submit((event) => {

        event.preventDefault();
        let direction = $('#sortLocation').val();
        let url = 'rest/sort/sportskiObjekat/lokacija/' + direction;

        $.get({
            url: url,
            contentType: 'application/json',
            success: function (sportskiObjekti) {
                $("#tabelaSportskihObjekata tbody").html("");
                console.log(sportskiObjekti);
                for (let sportskiObjekat of sportskiObjekti) {
                    addSportskiObjekatTr(sportskiObjekat);
                }
                $('#naziv').val('');
                $('#tipObjekta').val('');
            },
        })
    });

    $('#sortByRating').submit((event) => {

        event.preventDefault();
        let direction = $('#sortRating').val();
        let url = 'rest/sort/sportskiObjekat/prosecnaOcena/' + direction;

        $.get({
            url: url,
            contentType: 'application/json',
            success: function (sportskiObjekti) {
                $("#tabelaSportskihObjekata tbody").html("");
                console.log(sportskiObjekti);
                for (let sportskiObjekat of sportskiObjekti) {
                    addSportskiObjekatTr(sportskiObjekat);
                }
                $('#naziv').val('');
                $('#tipObjekta').val('');
            },
        })
    });



    $('#sportskiObjektiform').submit((event) => {

        //window.alert("usao u submit");

        event.preventDefault();

        let naziv = $('#naziv').val();
        let tipObjekta = $('#tipObjekta').val();
        let prosecnaOcena = $('#prosecnaOcena').val();

        let url = 'rest/pretraga/';

        if (naziv === "" && tipObjekta === "" && prosecnaOcena === "0") {
            url = 'rest/sportskiObjekti';
            $.get({
                url: url,
                contentType: 'application/json',
                success: function (sportskiObjekti) {
                    $("#tabelaSportskihObjekata tbody").html("");
                    console.log(sportskiObjekti);
                    for (let sportskiObjekat of sportskiObjekti) {
                        addSportskiObjekatTr(sportskiObjekat);
                    }
                    $('#naziv').val('');
                    $('#tipObjekta').val('');
                },
            })
        }
        else if (naziv === "" && tipObjekta === "") {
            url = 'rest/sportskiObjektiPoOceni/' + prosecnaOcena;
            $.get({
                url: url,
                contentType: 'application/json',
                success: function (sportskiObjekti) {
                    $("#tabelaSportskihObjekata tbody").html("");
                    console.log(sportskiObjekti);
                    for (let sportskiObjekat of sportskiObjekti) {
                        addSportskiObjekatTr(sportskiObjekat);
                    }
                    $('#naziv').val('');
                    $('#tipObjekta').val('');
                },
            })
        }
        else if (naziv === "") {
            url += "findTipObjekta/" + tipObjekta + "&" + prosecnaOcena;
            $.get({
                url: url,
                contentType: 'application/json',
                success: function (sportskiObjekti) {
                    $("#tabelaSportskihObjekata tbody").html("");
                    console.log(sportskiObjekti);
                    for (let sportskiObjekat of sportskiObjekti) {
                        addSportskiObjekatTr(sportskiObjekat);
                    }
                    $('#naziv').val('');
                    $('#tipObjekta').val('');
                },
            })
        }
        else if (tipObjekta === "") {
            url += "findNaziv/" + naziv + "&" + prosecnaOcena;
            $.get({
                url: url,
                contentType: 'application/json',
                success: function (sportskiObjekti) {
                    $("#tabelaSportskihObjekata tbody").html("");
                    console.log(sportskiObjekti);
                    for (let sportskiObjekat of sportskiObjekti) {
                        addSportskiObjekatTr(sportskiObjekat);
                    }
                    $('#naziv').val('');
                    $('#tipObjekta').val('');
                },
            })
        }
        else {
            url += "findObjekat/" + naziv + "&" + tipObjekta + "&" + prosecnaOcena;
            $.get({
                url: url,
                contentType: 'application/json',
                success: function (sportskiObjekti) {
                    $("#tabelaSportskihObjekata tbody").html("");
                    console.log(sportskiObjekti);
                    for (let sportskiObjekat of sportskiObjekti) {
                        addSportskiObjekatTr(sportskiObjekat);
                    }
                    $('#naziv').val('');
                    $('#tipObjekta').val('');
                },
            })
        }



    });

});
