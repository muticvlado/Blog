
function getDateAgo(date) {
	return moment(date, "YYYYMMDD").fromNow();
}

function getDateddMMM(date) {
	return moment(date).format("d MMM") + " | " + moment(date).format("YYYY");
}

function getDateMMMdd(date) {
	return moment(date).format("MMM d") + ", " + moment(date).format("YYYY");
}

function displayDate() {

	var spans = document.getElementsByClassName("dateAgo");

	for (i = 0; i < spans.length; i++) {
		spans[i].innerText = getDateAgo(spans[i].innerText);
	}

	spans = document.getElementsByClassName("dateUp");

	for (i = 0; i < spans.length; i++) {
		spans[i].innerText = getDateddMMM(spans[i].innerText);
	}

	spans = document.getElementsByClassName("footerDate");

	for (i = 0; i < spans.length; i++) {
		spans[i].innerText = getDateMMMdd(spans[i].innerText);
	}
}		