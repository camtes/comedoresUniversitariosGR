<?
include('simple_html_dom.php');
header('Content-Type: text/html; charset=UTF-8');
// Create DOM from URL or file
$html = file_get_html('http://scu.ugr.es/');

$count = 0;
$json = array();
$menudia = array();
$actualizado = false;

$fecha = null;
$plato1 = null;
$plato2 = null;
$plato3 = null;
$plato4 = null;

if ($actualizado) {
  // enviar push a todo quisqui
  $actualizado = false;
}

// Find all links
foreach($html->find('td') as $element) {
  if (strpos($element, "MARTES") or strpos($element, "MIÉRCOLES") or strpos($element, "JUEVES") or strpos($element, "VIERNES") or strpos($element, "SÁBADO") ) {
    $count = 0;

    $menudia = array(
      'fecha' => $fecha,
      'plato1' => $plato1,
      'plato2' => $plato2,
      'plato3' => $plato3,
      'plato4' => $plato4);

    // Inicializamos de nuevo todo a null
    $fecha = null;
    $plato1 = null;
    $plato2 = null;
    $plato3 = null;
    $plato4 = null;

    // añado el día;
    array_push($json, $menudia);

    // limpio array auxiliar
    unset($menudia);
  }

  switch($count){
    case 0:
      $fecha = $element->plaintext;
      break;
    case 1:
      $plato1 = $element->plaintext;
      break;
    case 2:
      $plato2 = $element->plaintext;
      //array_push($menudia, 'plato2: $element->plaintext');
      break;
    case 3:
      $plato3 = $element->plaintext;
      //array_push($menudia, "'plato3':'$element->plaintext'");
      break;
    case 4:
      $plato4 = $element->plaintext;
      //array_push($menudia, "'plato4':'$element->plaintext'");
      break;
  }
  $count = $count +1 ;
}

echo json_encode($json);
?>
