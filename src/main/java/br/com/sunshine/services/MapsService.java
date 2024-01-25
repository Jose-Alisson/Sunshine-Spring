package br.com.sunshine.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapsService {
	
	@Value("${google.maps.key}")
	private String key;
	
	private WebClient http = WebClient.create();
	
	public Map<String, Object> findDistaceToAddress(String origin, String destination) {
		String url = String.format("https://maps.googleapis.com/maps/api/directions/xml?origin=%s&destination=%s&key=%s", origin, destination, key);
		String res = http.get().uri(url).retrieve().bodyToMono(String.class).block();

		XmlMapper mapper = new XmlMapper();
		Map<String, Object> map = new HashMap<>();

		try {

			var leg = mapper.readTree(res).path("route").path("leg");
			map.put("distance", leg.path("distance").path("value").asInt());
			map.put("distance_text", leg.path("distance").path("text").asText());
			map.put("end_address", leg.path("end_address"));

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return map;
	}
}
