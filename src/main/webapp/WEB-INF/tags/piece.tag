<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the piece to show" %>
 <%@ attribute name="piece" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.game.Piece"
 description="Piece to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 var image = document.getElementById('${piece.color}');
 ctx.drawImage(image,${piece.getPositionXInPixels()},${piece.getPositionYInPixels()},${size},${size});
 </script>