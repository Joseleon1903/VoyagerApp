console.log("load flow-main");

var myDiagram;
var $;
var modestate = 'false';

function init() {
    console.log("entered init::...")

    $ = go.GraphObject.make;
    myDiagram = $(go.Diagram, "main-flow-context",{
        initialContentAlignment: go.Spot.Center,
        "undoManager.isEnabled": true
      });

  myDiagram.nodeTemplate =
  $(go.Node, "Auto",
    $(go.Shape, "Rectangle",
      new go.Binding("fill", "color")),
    $(go.TextBlock,
      { margin: 6, font: "14px sans-serif" },
      new go.Binding("text", "key"))
  );
    

    var myModel = $(go.Model);
    // in the model data, each node is represented by a JavaScript object:
    var nodeDataArray = [];
    var linkDataArray = [];
    myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
}

//update diagram
function updateEvent(nodeData, linkData) {
    console.log("entered updateEvent::..");
    var $ = go.GraphObject.make;
    myDiagram.model = new go.GraphLinksModel(nodeData, linkData);
}

//validando selected node
function validateSelectedNode() {
    var it = myDiagram.selection.iterator;
    while (it.hasNext()) {
        return false;
    }
    return true;
}

function checkStatus(){
    console.log("entered estatus change");
    var checkBox = document.getElementById("modeStatus");
  // If the checkbox is checked, display the output text
    if (checkBox.checked == true){
        init();
    } else {
      var nodeDataArray = [];
      var linkDataArray = [];
       myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
    } 
}
