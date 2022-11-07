<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<body style="background: url(/resources/images/backgroundPetris.jpg)">
	<petclinic:layout pageName="home">

		<br>
		<div align="center" style="margin-left: 50px; margin-top: -20px;">

			<spring:url value="/resources/images/videoPetris3.gif"
				htmlEscape="true" var="videoPetris" />
			<img id="f1" class="img-responsive"
				style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
				src="${videoPetris}" width="720" height="450" />
		</div>
		<security:authorize access="hasAuthority('persona')">
			<section id="sectionA">
				<br></br>

				<div class="row" id="popa">
					<h2>Bienvenido a Petris</h2>


				</div>

				<div class="row">

					<div class="col-md-4">
						<br>

						<p>
							<br>


							<spring:url value="/resources/images/FotoError.jpg"
								htmlEscape="true" var="error" />
							<img id="f2" class="img-responsive"
								style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
								src="${error}" /> <br>
						<p>
							<button class="btn btn-default" style="margin-left: 30%"
								onclick="window.location.href='games/new'">
								<span>Juega con amigos</span>
							</button>
						</p>


						<h2></h2>
						<br></br> <br>


						<p>
					</div>

					<div class="col-md-4">

						<br></br>
						<p>

							<spring:url value="/resources/images/pick65 (1).png"
								htmlEscape="true" var="error3" />

							<img id="f3" class="img-responsive"
								style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
								src="${error3}" /> <br>
							<button class="btn btn-default" style="margin-left: 30%"
								onclick="window.location.href='https://youtu.be/YvKUSkq8dEg'">
								<span>¡Aprende a jugar!</span>
							</button>
						<h2></h2>
						<br></br> <br>


						<p>
					</div>

					<div class="col-md-4">
						<br></br>

						<p>
							<spring:url value="/resources/images/img23 (1).jpg"
								htmlEscape="true" var="error2" />
							<img id="f4" class="img-responsive"
								style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
								src="${error2}" /> <br>
							<button class="btn btn-default" style="margin-left: 27%"
								onclick="window.location.href='personas'">
								<span>Historial de partidas</span>
							</button>
						<h2></h2>
						<br></br> <br>


						<p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<br>

						<p>
							<br>


							<spring:url value="/resources/images/FotoError.jpg"
								htmlEscape="true" var="error" />
							<img id="f2" class="img-responsive"
								style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
								src="${error}" /> <br>
						<p>
							<button class="btn btn-default" style="margin-left: 30%"
								onclick="window.location.href='people'">
								<span>Modo espectador</span>
							</button>
						</p>


						<h2></h2>
						<br></br> <br>


						<p>
					</div>
					<div class="col-md-5">
						<br>

						<p>
							<br>


							<spring:url value="/resources/images/FotoError.jpg"
								htmlEscape="true" var="error" />
							<img id="f2" class="img-responsive"
								style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
								src="${error}" /> <br>
						<p>
							<button class="btn btn-default" style="margin-left: 25%"
								onclick="window.location.href='estadisticas'">
								<span>Estadísticas y más</span>
							</button>
						</p>


						<h2></h2>
						<br></br> <br>


						<p>
					</div>
					<div class="col-md-1"></div>
				</div>
			</section>
		</security:authorize>

		<security:authorize access="hasAuthority('admin')">
			<br></br>
			<br>
			<section id="sectionB">
				<div align="center" style="margin-left: 20px; margin-top: -20px;">

					<spring:url value="/resources/images/petris_detalle_progreso1.jpg"
						htmlEscape="true" var="error6" />
					<img id="f5" width="720" height="450" class="img-responsive"
						style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
						src="${error6}" /> <br>
					<button class="btn btn-default" style="margin-left: -1%"
						onclick="window.location.href='/games/herramientasAdmin'">
						<span>Administración</span>
					</button>


				</div>
			</section>
		</security:authorize>
		<br></br>
		<br>
		<div class="col-md-12">
			<h3 style="text-align: center">¡Pásale este enlace a un amigo
				para que juegue a Petris contigo!</h3>
			<button class="btn btn-default" style="margin-left: 44%" id='getlink'
				href='javascript:void();'>
				<span>Copiar URL &nbsp;&nbsp; <svg
						xmlns="http://www.w3.org/2000/svg" width="22" height="22"
						fill="currentColor" class="bi bi-clipboard" viewBox="0 0 16 16">
  <path
							d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z" />
  <path
							d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z" />
</svg></span>
			</button>
		</div>
		<div class="col-md-8">



			<script>
				//<![CDATA[
				var boton = document.getElementById('getlink');
				boton
						.addEventListener(
								'click',
								function(e) {
									if (boton.id == 'getlink') {
										var aux = document
												.createElement('input');
										aux
												.setAttribute('value',
														window.location.href
																.split('?')[0]
																.split('#')[0]);
										document.body.appendChild(aux);
										aux.select();
										try {
											document.execCommand('copy');
											var aviso = document
													.createElement('div');
											aviso.setAttribute('id', 'aviso');
											aviso.style.cssText = 'position:fixed; z-index: 9999999; top: 50%;left:50%;margin-left: -70px;padding: 20px; background: #E8CEF5;border-radius: 8px ;border: 4px solid #853fb3;font-family: sans-serif;';
											aviso.innerHTML = '¡URL copiada con éxito!';
											document.body.appendChild(aviso);
											document.load = setTimeout(
													'document.body.removeChild(aviso)',
													2000);
											document.load = setTimeout(
													'boton.id = "getlink"',
													2500);
											boton.id = '';
										} catch (e) {
											alert('Tu navegador no soporta la función de copiar');
										}
										document.body.removeChild(aux);
									}
								});
				//]]>
			</script>
			<h2>${group}</h2>
			<ul>
				<c:forEach items="${personas}" var="person">
					<li>${person.firstName}&nbsp;${person.lastName}</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-md-4">
			<spring:url value="/resources/images/logoUniversidad_3.png"
				htmlEscape="true" var="logoUniImage" />
			<img class="img-responsive" src="${logoUniImage}" />
		</div>
		</div>



	</petclinic:layout>
</body>
</html>
