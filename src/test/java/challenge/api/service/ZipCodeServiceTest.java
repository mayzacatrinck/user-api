package challenge.api.service;

import challenge.api.exception.ZipCodeException;
import challenge.api.exception.ZipCodeNotFoundException;
import challenge.api.integration.AddressViaCep;
import challenge.api.integration.ViaCepClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ZipCodeServiceTest {

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private ZipCodeService zipCodeService;

    @Test(expected = ZipCodeException.class)
    public void shouldThrowExceptionWhenErrorsoccur() {

        zipCodeService.findZipeCode("24057014");

        verify(viaCepClient, times(1)).findZipCode(any());
    }

    @Test(expected = ZipCodeNotFoundException.class)
    public void shouldThrowExceptionIfItDoesNotFindZipCode() {

        when(viaCepClient.findZipCode("24057014")).thenReturn(new AddressViaCep());

        zipCodeService.findZipeCode("24057014");

        verify(viaCepClient, times(1)).findZipCode(any());
    }

    @Test
    public void shouldFindZipCode() {
        AddressViaCep viaCep = new AddressViaCep();
        viaCep.setZipCode("25047056");
        viaCep.setStreet("Rua A");

        when(viaCepClient.findZipCode("25047056")).thenReturn(viaCep);

        zipCodeService.findZipeCode("25047056");
        verify(viaCepClient, times(1)).findZipCode(any());
    }
}
