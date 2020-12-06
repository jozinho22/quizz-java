<%@ include file="/WEB-INF/tags/begin.jsp"%>
<%@ include file="/WEB-INF/tags/navbar.jsp"%>

<div id="index-section" class="container">
	<div id="inner-section">
		<form action="game" method="GET">
			<div id="title-section">
				<h4 style="text-decoration: underline;">Du Java plus ce que
					vous voulez</h4>
			</div>
			<div id="desc-section">
				<div id="choice-section" class="container" class="form-check">
					<div class="row">
						<div class="col-sm">
							<!-- Default unchecked disabled -->
							<div class="form-check">
								<input type="checkbox" name="cb" value="SPRING" checked>
								<h5>
									<label class="form-check-label">Spring</label>
								</h5>
							</div>

							<div class="form-check">
								<input type="checkbox" name="cb" value="FRAMEWORKS" checked>
								<h5>
									<label class="form-check-label">Frameworks Java</label>
								</h5>
							</div>

							<div class="form-check">
								<input type="checkbox" name="cb" value="GIT" checked>
								<h5>
									<label class="form-check-label">Git</label>
								</h5>
							</div>

							<div class="form-check">
								<input type="checkbox" name="cb" value="MAVEN" checked>
								<h5>
									<label class="form-check-label">Maven</label>
								</h5>
							</div>

						</div>
						<div class="col-sm">

							<div class="form-check">
								<input type="checkbox" name="cb" value="DESIGN_PATTERNS" checked>
								<h5>
									<label class="form-check-label">Design patterns</label>
								</h5>
							</div>

							<div class="form-check">
								<input type="checkbox" name="cb" value="DIVERS" checked>
								<h5>
									<label class="form-check-label">Divers</label>
								</h5>
							</div>

							<div class="form-check">
								<input type="checkbox" name="cb" value="SQL" checked>
								<h5>
									<label class="form-check-label">Sql</label>
								</h5>
							</div>

							<div class="form-check">
								<input type="checkbox" name="cb" value="ALGO" checked>
								<h5>
									<label class="form-check-label">Algo</label>
								</h5>
							</div>

						</div>
					</div>
				</div>

			</div>
			<br>

			<!-- 				<input id="input" name="uuid" type="text" class="form-control" hidden=true> -->
			<input id="sessionTheme" name="theme" value="dark" hidden="true">
			<div id="start-btn">
				<button class="btn" type="submit">C'est parti !</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/tags/end.jsp"%>