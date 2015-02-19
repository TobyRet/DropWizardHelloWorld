<html>
	<head>
		<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class='container'>
			<div class ="col-md-8 col-md-offset-2">
				<h1>List a new music gig</h1>
				<form action="/gigs/create" method="post">
					<div class="form-group">
						<label for="eventName">Event Name</label>
						<input type="text" class="form-control" id="gigListingName">
					</div>
					<div class="form-group">
						<label for="artist">Artist</label>
						<input type="text" class="form-control" id="gigListingArtist" >
					</div>
					<div class="form-group">
						<label for="eventDate">Event Date</label>
						<input type="date" class="form-control" id="gigListingDate">
					</div>
					<div class="form-group">
						<label for="eventLocation">Location</label>
						<input type="text" class="form-control" id="gigListingLocation" >
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</body>
</html>