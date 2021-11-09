<%@ attribute name="board" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.game.Board"
 description="Board to be rendered" %>
<canvas id="canvas" width="${board.width}" height="${board.height}"></canvas>
<img id="source" src="${board.background}" style="display:none">
<img id="black" src="/resources/images/black.png" style="display:none">
<img id="red" src="/resources/images/red.png" style="display:none">
<script>
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var image = document.getElementById('source');

ctx.drawImage(image, 0, 0, ${board.width}, ${board.height});
</script>