package com.intensostudios.grokit.lang.google.texttospeech;

import com.google.auth.Credentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class GoogleTextToSpeech implements TextToSpeechService {

    private Credentials creds;

    public GoogleTextToSpeech(Credentials creds) {
        this.creds = creds;
    }

    @Override
    public byte[] transform(String text) {
        // Instantiates a client
        try {
            TextToSpeechSettings settings = TextToSpeechSettings.newBuilder().setCredentialsProvider(() -> creds).build();
            TextToSpeechClient client = TextToSpeechClient.create(settings);
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText(text)
                    .build();

            // Build the voice request, select the language code ("en-US") and the ssml voice gender
            // ("neutral")
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // Perform the text-to-speech request on the text input with the selected voice parameters and
            // audio file type
            SynthesizeSpeechResponse response = client.synthesizeSpeech(input, voice,
                    audioConfig);

            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();
            return audioContents.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToMp3File(byte[] data, String path) {

        // Write the response to the output file.
        try (OutputStream out = new FileOutputStream(path)) {
            out.write(data);
            System.out.printf("Audio content written to file %s", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
