<script>
	import { onMount } from "svelte";

	let incidentId;
	let message = "... loading ..."
	let response;

	const host = window.location.hostname;
	const backendUri = production() ? "https://basedefender.de:44301" : "https://" + host + ":44301";
	const self = production() ? "https://" + host : "http://" + host + ":5000";

	onMount(async () => {
		getUrlParams();
		response = confirmRemoval();
	});

	function production() {
		return host == "basedefender.de";
	}

	function getUrlParams() {
		const queryString = window.location.search;
		const urlParams = new URLSearchParams(queryString);
        
        if (urlParams.has('id')) {
            incidentId = urlParams.get('id');
        }
	}

	function confirmRemoval() {
		let result = [];
		if (incidentId === undefined || incidentId === "") {
			message = "ID not found."
		} else {
			result = fetch(backendUri + "/incidents/" + incidentId)
			.then(response=>response.text())
			.then(data=> message=data)
		}
		return result;
	}

</script>

<main>
	<h1>{message}</h1>
</main>

<div class="footer">
	GDPR Shield © 2021
</div>

<style>
	main {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 4em;
		font-weight: 100;
	}

	.footer {
		position: fixed;
		left: 0;
		bottom: 0;
		width: 100%;
		text-align: center;
		color: #a1a1a1;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>