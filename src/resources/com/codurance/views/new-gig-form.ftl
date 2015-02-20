<html>
	<head>
		<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class='container'>
			<div class ="col-md-8 col-md-offset-2">
				<h1>List a new music gig</h1>
				<form action="/events/create" method="post">
					<div class="form-group">
						<label for="gigListingName">Event Name</label>
						<input type="text" class="form-control" name="gigListingName">
					</div>
					<div class="form-group">
						<label for="gigListingArtist">Artist</label>
						<input type="text" class="form-control" name="gigListingArtist" >
					</div>
					<div class="form-group">
						<label for="gigListingGenre">Genre</label>
						<select class="form-control" type="text" name="gigListingGenre">
						  <option>Donk</option>
						  <option>Anything-core</option>
						  <option>Pirate Sludge Metal</option>
						  <option>Classical</option>
						  <option>Doom-pop</option>
						</select>
					</div>
					<div class="form-group">
						<label for="gigListingDate">Event Date</label>
						<input type="date" class="form-control" name="gigListingDate">
					</div>
					<div class="form-group">
						<label for="gigListingLocation">Location</label>
						<input type="text" class="form-control" name="gigListingLocation" >
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</body>
</html>