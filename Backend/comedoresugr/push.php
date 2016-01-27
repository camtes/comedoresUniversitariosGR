<?
$timezone_identifier = "Europe/Madrid";
date_default_timezone_set($timezone_identifier);
$weekday = null;
$day = null;

$today = getdate();
$weekday = $today['weekday'];

  switch ($weekday) {
    case 'Monday':
      $day = "Lunes";
      break;
    case 'Tuesday':
      $day = "Martes";
      break;
    case 'Wednesday':
      $day = "Miercoles";
      break;
    case 'Thursday':
      $day = "Jueves";
      break;
    case 'Friday':
      $day = "Viernes";
      break;
  }

  


?>
