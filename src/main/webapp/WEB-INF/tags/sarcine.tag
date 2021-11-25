<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the sarcine to show" %>
 <%@ attribute name="sarcine" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.game.Sarcine"
 description="Sarcine to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 //var image = document.getElementById('${piece.color}');
 var image = document.getElementById('${sarcine.getURLimage(size)}');
 
 ctx.drawImage(image,${sarcine.getPositionXInPixels(size)},${sarcine.getPositionYInPixels(size)},${size},${size});
 </script>