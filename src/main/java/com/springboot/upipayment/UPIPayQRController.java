package com.springboot.upipayment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@RestController
@RequestMapping("api/v1/QRCodeGenerate")
public class UPIPayQRController {
	private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);

	private final Map hintMap = new HashMap<>();
	private final String charset = StandardCharsets.UTF_8.name(); // or "ISO-8859-1"
	private final int size = 350;

	public class QRCodeService {
		public QRCodeService() {
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			hintMap.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
			hintMap.put(EncodeHintType.MARGIN, 1);

			hintMap.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
			hintMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			hintMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
		}
	}

	@PostMapping("/getQRCodeBase64")
	public String createUpiQrCode(@RequestBody RequestForm req) throws IOException, WriterException {

		final String qrCode = UriComponentsBuilder.fromUriString("upi://pay").queryParam("pa", req.payeeAddress)
				.queryParam("pn", req.payeeName).queryParam("tn", req.trxNo).queryParam("tr", req.trxRef)
				.queryParam("am", req.amount).queryParam("cu", req.currency).queryParam("purpose", req.purpose).build()
				.encode().toUriString();
		return createQRCode(qrCode);
	}

	public String createQRCode(String qrCodeData) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, size, size, hintMap);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "png", baos);
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
}
