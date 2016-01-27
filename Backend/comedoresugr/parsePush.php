<?
/**
 * Send a push
 * @param array $data: title, text, idCampaign
 * @param array $devices: array with device ids, empty to send to all people who have the app installed
 */
function sendPush($data=array(), $channels=array()){
	$responses = 0;
	$failures = 0;

	$wheres = array();

	$dataDefaults = array(
		'title' => '',
		'message' => '',
	);

	$data = array_merge($dataDefaults, $data);

	// PARSE URL
	$url = get_option(VIRDE_PREFIX.'notification_parse_url');
	// APPLICATION ID
	$appId = get_option(VIRDE_PREFIX.'notification_parse_app_id');
	// APPLICATION REST API KEY
	$restKey = get_option(VIRDE_PREFIX.'notification_parse_app_key');

	//enviamos a todos
	//$target_device = 'TARGET_INSTALLATION_OBJECT_ID_HERE';  // using object Id of target Installation.

	// For ALL Devices
	if(0 == count($channels))
		$where = "{}";
	// For Some Devices
	else
		$where = array("objectId" => array( 'in' => array(array_values($channels))));

	$push_payload = json_encode(array(
        "where" => $where,
				//"channels" => ["PHPFans"],
        "data" => $data
	));

	$rest = curl_init();
	curl_setopt($rest, CURLOPT_URL, $url);
	curl_setopt($rest, CURLOPT_PORT, 443);
	curl_setopt($rest, CURLOPT_POST, 1);
	curl_setopt($rest, CURLOPT_POSTFIELDS, $push_payload);
	curl_setopt($rest, CURLOPT_HTTPHEADER,
		array(
			"X-Parse-Application-Id: " . $appId,
			"X-Parse-REST-API-Key: " . $restKey,
			"Content-Type: application/json"
		)
	);

	$response = curl_exec($rest);

	curl_close($rest);

	if(json_encode($response))
		$responses++;
	else
		$failures++;

	return array($responses, $failures);

}
?>
