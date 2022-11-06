package com.challenge.gamestoreinvoicing.service;

import com.challenge.gamestoreinvoicing.feign.GameStoreCatalogFeignClient;
import com.challenge.gamestoreinvoicing.model.*;
import com.challenge.gamestoreinvoicing.repository.InvoiceRepository;
import com.challenge.gamestoreinvoicing.repository.ProcessingFeeRepository;
import com.challenge.gamestoreinvoicing.repository.TaxRepository;
import com.challenge.gamestoreinvoicing.viewModel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GameStoreInvoiceServiceLayerTest {

    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    TaxRepository taxRepository;

    GameStoreCatalogFeignClient clientTShirt;
    GameStoreCatalogFeignClient clientConsole;
    GameStoreCatalogFeignClient clientGame;
    GameStoreInvoiceServiceLayer service;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpTaxRepositoryMock();
        setUpConsoleFeignClientMock();
        setUpGameFeignClientMock();
        setUpTShirtFeignClientMock();

        service = new GameStoreInvoiceServiceLayer(clientConsole, clientGame, clientTShirt, invoiceRepository, taxRepository, processingFeeRepository);
    }

    // TShirt Feign Client Tests
    @Test
    public void shouldReturnTShirtById() {
        // Arrange and Act
        Optional<TShirt> actualTShirt = Optional.ofNullable(clientTShirt.getTShirt(54));

        // Assert
        assertTrue(actualTShirt.isPresent());
    }

    @Test
    public void shouldReturnTShirtBySize() {
        // Arrange
        String expectedTShirtSize = "Large";

        // Act
        List<TShirt> tShirtList = clientTShirt.getTShirtsBySize("Large");

        // Assert
        String actualTShirtSize;
        for(TShirt actualTShirt: tShirtList) {
            actualTShirtSize = actualTShirt.getSize();
            assertEquals(expectedTShirtSize, actualTShirtSize);
        }
    }

    @Test
    public void shouldReturnTShirtByColor() {
        // Arrange
        String expectedTShirtColor = "Blue";

        // Act
        List<TShirt> tShirtList = clientTShirt.getTShirtsByColor("Blue");

        // Assert
        String actualTShirtColor;
        for(TShirt actualTShirt: tShirtList) {
            actualTShirtColor = actualTShirt.getColor();
            assertEquals(expectedTShirtColor, actualTShirtColor);
        }
    }

    @Test
    public void shouldDeleteTShirtById() {
        // Arrange
        Optional<TShirt> tShirt = Optional.ofNullable(clientTShirt.getTShirt(55));
        assertTrue(tShirt.isPresent());

        // Act
        clientTShirt.deleteTShirt(55);

        // Assert
        verify(clientTShirt, times(1)).deleteTShirt(55);
    }

    @Test
    public void shouldUpdateTShirtById() {
        // Arrange
        TShirt saveTShirt = new TShirt();
        saveTShirt.setId(54);
        saveTShirt.setSize("Large");
        saveTShirt.setColor("Blue");
        saveTShirt.setDescription("Long Sleeve");
        saveTShirt.setPrice(new BigDecimal("15.99"));
        saveTShirt.setQuantity(450);

        // Act
        clientTShirt.updateTShirt(saveTShirt);

        // Assert
        verify(clientTShirt, times(1)).updateTShirt(saveTShirt);
    }

    @Test
    public void shouldReturnAllTShirt() {
        // Arrange
        TShirt saveTShirt = new TShirt();
        saveTShirt.setId(54);
        saveTShirt.setSize("Large");
        saveTShirt.setColor("Blue");
        saveTShirt.setDescription("Long Sleeve");
        saveTShirt.setPrice(new BigDecimal("19.99"));
        saveTShirt.setQuantity(4);

        TShirt saveTShirt2 = new TShirt();
        saveTShirt2.setId(55);
        saveTShirt2.setSize("Medium");
        saveTShirt2.setColor("Red");
        saveTShirt2.setDescription("V-Neck");
        saveTShirt2.setPrice(new BigDecimal("19.99"));
        saveTShirt2.setQuantity(150);

        List<TShirt> expectedTShirtList = new ArrayList<>();
        expectedTShirtList.add(saveTShirt);
        expectedTShirtList.add(saveTShirt2);

        // Act
        List<TShirt> actualTShirtList = clientTShirt.getAllTShirts();

        // Assert
        assertEquals(expectedTShirtList, actualTShirtList);
    }

    // Console Feign Client Tests
    @Test
    public void shouldReturnConsoleById() {
        // Arrange and Act
        Optional<Console> actualConsole = Optional.ofNullable(clientConsole.getConsole(7));

        // Assert
        assertTrue(actualConsole.isPresent());
    }

    @Test
    public void shouldReturnConsoleByManufacturer() {
        // Arrange
        String expectedManufacturer = "Sony";

        // Act
        List<Console> consoleList = clientConsole.getConsoleByManufacturer("Sony");

        // Assert
        String actualManufacturer;
        for(Console actualConsole: consoleList) {
            actualManufacturer = actualConsole.getManufacturer();
            assertEquals(expectedManufacturer, actualManufacturer);
        }
    }

    @Test
    public void shouldDeleteConsoleById() {
        // Arrange
        Optional<Console> console = Optional.ofNullable(clientConsole.getConsole(8));
        assertTrue(console.isPresent());

        // Act
        clientConsole.deleteConsole(8);

        // Assert
        verify(clientConsole, times(1)).deleteConsole(8);
    }

    @Test
    public void shouldUpdateConsole() {
        // Arrange
        Console saveConsole = new Console();
        saveConsole.setId(7);
        saveConsole.setModel("PS 5");
        saveConsole.setManufacturer("Sony");
        saveConsole.setMemoryAmount("512 GB");
        saveConsole.setProcessor("AMD Zen 2");
        saveConsole.setPrice(new BigDecimal("499.95"));
        saveConsole.setQuantity(501);

        // Act
        clientConsole.updateConsole(saveConsole);

        // Assert
        verify(clientConsole, times(1)).updateConsole(saveConsole);
    }

    @Test
    public void shouldReturnAllConsole() {
        // Arrange
        Console saveConsole = new Console();
        saveConsole.setId(7);
        saveConsole.setModel("PS 5");
        saveConsole.setManufacturer("Sony");
        saveConsole.setMemoryAmount("64 GB");
        saveConsole.setProcessor("AMD Zen 2");
        saveConsole.setPrice(new BigDecimal("499.95"));
        saveConsole.setQuantity(50);

        Console saveConsole2 = new Console();
        saveConsole2.setId(8);
        saveConsole2.setModel("XBox Series X");
        saveConsole2.setManufacturer("Microsoft");
        saveConsole2.setMemoryAmount("512 GB");
        saveConsole2.setProcessor("AMD Zen 2");
        saveConsole2.setPrice(new BigDecimal("549.95"));
        saveConsole2.setQuantity(50);

        List<Console> expectedConsoleList = new ArrayList<>();
        expectedConsoleList.add(saveConsole);
        expectedConsoleList.add(saveConsole2);

        // Act
        List<Console> actualConsoleList = clientConsole.getAllConsoles();

        // Assert
        assertEquals(expectedConsoleList, actualConsoleList);
    }
    // Game Feign Client Test
    @Test
    public void shouldReturnGameById() {
        // Arrange and Act
        Optional<Game> actualGame = Optional.ofNullable(clientGame.getGameInfo(3));

        // Assert
        assertTrue(actualGame.isPresent());
    }

    @Test
    public void shouldReturnGameByEsrb() {
        // Arrange
        String expectedGameEsrbRating = "10+";

        // Act
        List<Game> gameList = clientGame.getGamesByEsrbRating("10+");

        // Assert
        String actualGameEsrbRating;
        for (Game actualGame: gameList) {
            actualGameEsrbRating = actualGame.getEsrbRating();
            assertEquals(expectedGameEsrbRating, actualGameEsrbRating);
        }
    }

    @Test
    public void shouldReturnGameByTitle() {
        // Arrange
        String expectedGameTitle = "Need For Speed Unbound";

        // Act
        List<Game> gameList = clientGame.getGamesByTitle("Need For Speed Unbound");

        // Assert
        String actualGameTitle;
        for (Game actualGame : gameList) {
            actualGameTitle = actualGame.getTitle();
            assertEquals(expectedGameTitle, actualGameTitle);
        }
    }

    @Test
    public void shouldReturnGameByStudio() {
        // Arrange
        String expectedGameStudio = "Electronic Arts";

        // Act
        List<Game> gameList = clientGame.getGamesByStudio("Electronic Arts");

        // Assert
        String actualGameStudio;
        for (Game actualGame: gameList) {
            actualGameStudio = actualGame.getStudio();
            assertEquals(expectedGameStudio, actualGameStudio);
        }
    }

    @Test
    public void shouldDeleteGameById() {
        // Arrange
        Optional<Game> game = Optional.ofNullable(clientGame.getGameInfo(4));
        assertTrue(game.isPresent());

        // Act
        clientGame.deleteGame(4);

        // Assert
        verify(clientGame, times(1)).deleteGame(4);
    }

    @Test
    public void shouldUpdateGame() {
        // Arrange
        Game expectedGame = new Game();
        expectedGame.setId(3);
        expectedGame.setTitle("Need For Speed Most Wanted");
        expectedGame.setEsrbRating("Teen");
        expectedGame.setDescription("Exciting car racing and evading using exotic cars");
        expectedGame.setPrice(new BigDecimal("59.95"));
        expectedGame.setStudio("Electronic Arts");
        expectedGame.setQuantity(700L);

        // Act
        clientGame.updateGame(expectedGame);

        // Assert
        verify(clientGame, times(1)).updateGame(expectedGame);;
    }

    @Test
    public void shouldReturnAllGames() {
        // Arrange
        Game expectedGame = new Game();
        expectedGame.setId(3);
        expectedGame.setTitle("Need For Speed Unbound");
        expectedGame.setEsrbRating("10+");
        expectedGame.setDescription("Exciting car racing with exotic cars");
        expectedGame.setPrice(new BigDecimal("59.95"));
        expectedGame.setStudio("Electronic Arts");
        expectedGame.setQuantity(499L);

        Game expectedGame2 = new Game();
        expectedGame2.setId(4);
        expectedGame2.setTitle("NBA 2K23");
        expectedGame2.setEsrbRating("10+");
        expectedGame2.setDescription("Michael Jordan Edition");
        expectedGame2.setPrice(new BigDecimal("59.95"));
        expectedGame2.setStudio("Take-Two Interactive");
        expectedGame2.setQuantity(500L);

        List<Game> expectedGameList = new ArrayList<>();
        expectedGameList.add(expectedGame);
        expectedGameList.add(expectedGame2);

        // Act
        List<Game> actualGameList = clientGame.getAllGames();

        // Assert
        assertEquals(expectedGameList, actualGameList);
    }

    //Testing Invoice Operations...
    @Test
    public void shouldCreateFindInvoice() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = clientTShirt.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(54L);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test
    public void shouldFindAllInvoices(){
        InvoiceViewModel savedInvoice1 = new InvoiceViewModel();
        savedInvoice1.setName("Sandy Beach");
        savedInvoice1.setStreet("123 Main St");
        savedInvoice1.setCity("any City");
        savedInvoice1.setState("NY");
        savedInvoice1.setZipcode("10016");
        savedInvoice1.setItemType("T-Shirt");
        savedInvoice1.setItemId(12);//pretending item exists with this id...
        savedInvoice1.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice1.setQuantity(2);
        savedInvoice1.setSubtotal(savedInvoice1.getUnitPrice().multiply(new BigDecimal(savedInvoice1.getQuantity())));
        savedInvoice1.setTax(savedInvoice1.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice1.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice1.setTotal(savedInvoice1.getSubtotal().add(savedInvoice1.getTax()).add(savedInvoice1.getProcessingFee()));
        savedInvoice1.setId(22);

        InvoiceViewModel savedInvoice2 = new InvoiceViewModel();
        savedInvoice2.setName("Rob Bank");
        savedInvoice2.setStreet("888 Main St");
        savedInvoice2.setCity("any town");
        savedInvoice2.setState("NJ");
        savedInvoice2.setZipcode("08234");
        savedInvoice2.setItemType("Console");
        savedInvoice2.setItemId(120);//pretending item exists with this id...
        savedInvoice2.setUnitPrice(new BigDecimal("129.50"));//pretending item exists with this price...
        savedInvoice2.setQuantity(1);
        savedInvoice2.setSubtotal(savedInvoice2.getUnitPrice().multiply(new BigDecimal(savedInvoice2.getQuantity())));
        savedInvoice2.setTax(savedInvoice2.getSubtotal().multiply(new BigDecimal("0.08")));
        savedInvoice2.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice2.setTotal(savedInvoice2.getSubtotal().add(savedInvoice2.getTax()).add(savedInvoice2.getProcessingFee()));
        savedInvoice2.setId(12);

        InvoiceViewModel savedInvoice3 = new InvoiceViewModel();
        savedInvoice3.setName("Sandy Beach");
        savedInvoice3.setStreet("123 Broad St");
        savedInvoice3.setCity("any where");
        savedInvoice3.setState("CA");
        savedInvoice3.setZipcode("90016");
        savedInvoice3.setItemType("Game");
        savedInvoice3.setItemId(19);//pretending item exists with this id...
        savedInvoice3.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice3.setQuantity(4);
        savedInvoice3.setSubtotal(savedInvoice3.getUnitPrice().multiply(new BigDecimal(savedInvoice3.getQuantity())));
        savedInvoice3.setTax(savedInvoice3.getSubtotal().multiply(new BigDecimal("0.09")));
        savedInvoice3.setProcessingFee(BigDecimal.ZERO);
        savedInvoice3.setTotal(savedInvoice3.getSubtotal().add(savedInvoice3.getTax()).add(savedInvoice3.getProcessingFee()));
        savedInvoice3.setId(73);

        List<InvoiceViewModel> currInvoices = new ArrayList<>();
        currInvoices.add(savedInvoice1);
        currInvoices.add(savedInvoice2);
        currInvoices.add(savedInvoice3);

        List<InvoiceViewModel> foundAllInvoices = service.getAllInvoices();

        assertEquals(currInvoices.size(), foundAllInvoices.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateFindInvoiceWithBadState() {
        TShirt tShirt = new TShirt();
        tShirt.setId(99);
        tShirt.setSize("Small");
        tShirt.setColor("Red");
        tShirt.setDescription("sleeveless");
        tShirt.setPrice(new BigDecimal("400"));
        tShirt.setQuantity(30);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NY");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(99);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateFindInvoiceWithBadItemType() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = clientTShirt.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("Bad Item Type");
        invoiceViewModel.setItemId(54);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateFindInvoiceWithNoInventory() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
//        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(54L);
        invoiceViewModel.setQuantity(6);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateInvoiceInvalidItem() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("nothing");
        invoiceViewModel.setItemId(54L);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateInvoiceInvalidQuantity() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(54L);
        invoiceViewModel.setQuantity(0);

        invoiceViewModel = service.createInvoice(invoiceViewModel);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenCreateInvoiceInvalidInvoiceMV() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);

        InvoiceViewModel invoiceViewModel = null;

        invoiceViewModel = service.createInvoice(invoiceViewModel);
    }

    //DAO Mocks...
    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice = new Invoice();
        invoice.setName("John Jake");
        invoice.setStreet("street");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipcode("83749");
        invoice.setItemType("T-Shirt");
        invoice.setItemId(54L);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("39.98"));
        invoice.setTax(new BigDecimal("2"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("43.96"));

        Invoice invoice1 = new Invoice();
        invoice1.setId(20);
        invoice1.setName("John Jake");
        invoice1.setStreet("street");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipcode("83749");
        invoice1.setItemType("T-Shirt");
        invoice1.setItemId(54L);
        invoice1.setUnitPrice(new BigDecimal("19.99"));
        invoice1.setQuantity(2);
        invoice1.setSubtotal(new BigDecimal("39.98"));
        invoice1.setTax(new BigDecimal("2"));
        invoice1.setProcessingFee(new BigDecimal("1.98"));
        invoice1.setTotal(new BigDecimal("43.96"));

        doReturn(invoice1).when(invoiceRepository).save(invoice);
        doReturn(Optional.of(invoice1)).when(invoiceRepository).findById(20L);

        //Get All...
        Invoice savedInvoice1 = new Invoice();
        savedInvoice1.setName("Sandy Beach");
        savedInvoice1.setStreet("123 Main St");
        savedInvoice1.setCity("any City");
        savedInvoice1.setState("NY");
        savedInvoice1.setZipcode("10016");
        savedInvoice1.setItemType("T-Shirt");
        savedInvoice1.setItemId(12);//pretending item exists with this id...
        savedInvoice1.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice1.setQuantity(2);
        savedInvoice1.setSubtotal(savedInvoice1.getUnitPrice().multiply(new BigDecimal(savedInvoice1.getQuantity())));
        savedInvoice1.setTax(savedInvoice1.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice1.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice1.setTotal(savedInvoice1.getSubtotal().add(savedInvoice1.getTax()).add(savedInvoice1.getProcessingFee()));
        savedInvoice1.setId(22);

        Invoice savedInvoice2 = new Invoice();
        savedInvoice2.setName("Rob Bank");
        savedInvoice2.setStreet("888 Main St");
        savedInvoice2.setCity("any town");
        savedInvoice2.setState("NJ");
        savedInvoice2.setZipcode("08234");
        savedInvoice2.setItemType("Console");
        savedInvoice2.setItemId(120);//pretending item exists with this id...
        savedInvoice2.setUnitPrice(new BigDecimal("129.50"));//pretending item exists with this price...
        savedInvoice2.setQuantity(1);
        savedInvoice2.setSubtotal(savedInvoice2.getUnitPrice().multiply(new BigDecimal(savedInvoice2.getQuantity())));
        savedInvoice2.setTax(savedInvoice2.getSubtotal().multiply(new BigDecimal("0.08")));
        savedInvoice2.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice2.setTotal(savedInvoice2.getSubtotal().add(savedInvoice2.getTax()).add(savedInvoice2.getProcessingFee()));
        savedInvoice2.setId(12);

        Invoice savedInvoice3 = new Invoice();
        savedInvoice3.setName("Sandy Beach");
        savedInvoice3.setStreet("123 Broad St");
        savedInvoice3.setCity("any where");
        savedInvoice3.setState("CA");
        savedInvoice3.setZipcode("90016");
        savedInvoice3.setItemType("Game");
        savedInvoice3.setItemId(19);//pretending item exists with this id...
        savedInvoice3.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice3.setQuantity(4);
        savedInvoice3.setSubtotal(savedInvoice3.getUnitPrice().multiply(new BigDecimal(savedInvoice3.getQuantity())));
        savedInvoice3.setTax(savedInvoice3.getSubtotal().multiply(new BigDecimal("0.09")));
        savedInvoice3.setProcessingFee(BigDecimal.ZERO);
        savedInvoice3.setTotal(savedInvoice3.getSubtotal().add(savedInvoice3.getTax()).add(savedInvoice3.getProcessingFee()));
        savedInvoice3.setId(73);

        List<Invoice> allList = new ArrayList<>();
        allList.add(savedInvoice1);
        allList.add(savedInvoice2);
        allList.add(savedInvoice3);

        doReturn(allList).when(invoiceRepository).findAll();
    }

    private void setUpProcessingFeeRepositoryMock() {

        processingFeeRepository = mock(ProcessingFeeRepository.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("1.98"));
        processingFee.setProductType("T-Shirt");

        doReturn(Optional.of(processingFee)).when(processingFeeRepository).findById("T-Shirt");

    }

    private void setUpTaxRepositoryMock() {
        taxRepository = mock(TaxRepository.class);

        Tax taxNC = new Tax();
        taxNC.setRate(new BigDecimal(".05"));
        taxNC.setState("NC");

        Tax taxNY = new Tax();
        taxNY.setRate(BigDecimal.ZERO);
        taxNY.setState("NY");

        doReturn(Optional.of(taxNC)).when(taxRepository).findById("NC");
        doReturn(Optional.of(taxNY)).when(taxRepository).findById("NY");

    }

    // Feign Client Setup for Console
    private void setUpConsoleFeignClientMock() {
        clientConsole = mock(GameStoreCatalogFeignClient.class);
        Console console = new Console();
        console.setModel("PS 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("64 GB");
        console.setProcessor("AMD Zen 2");
        console.setPrice(new BigDecimal("499.95"));
        console.setQuantity(50);

        Console saveConsole = new Console();
        saveConsole.setId(7);
        saveConsole.setModel("PS 5");
        saveConsole.setManufacturer("Sony");
        saveConsole.setMemoryAmount("64 GB");
        saveConsole.setProcessor("AMD Zen 2");
        saveConsole.setPrice(new BigDecimal("499.95"));
        saveConsole.setQuantity(50);

        Console saveConsole2 = new Console();
        saveConsole2.setId(8);
        saveConsole2.setModel("XBox Series X");
        saveConsole2.setManufacturer("Microsoft");
        saveConsole2.setMemoryAmount("512 GB");
        saveConsole2.setProcessor("AMD Zen 2");
        saveConsole2.setPrice(new BigDecimal("549.95"));
        saveConsole2.setQuantity(50);

        doReturn(saveConsole).when(clientConsole).createConsole(console);
        doReturn(saveConsole).when(clientConsole).getConsole(7);
        doReturn(saveConsole2).when(clientConsole).getConsole(8);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(saveConsole);
        doReturn(consoleList).when(clientConsole).getConsoleByManufacturer("Sony");

        List<Console> consoleAll = new ArrayList<>();
        consoleAll.add(saveConsole);
        consoleAll.add(saveConsole2);
        doReturn(consoleAll).when(clientConsole).getAllConsoles();
    }

    // Feign Client Setup for Game
    private void setUpGameFeignClientMock() {
        clientGame = mock(GameStoreCatalogFeignClient.class);
        Game game = new Game();
        game.setTitle("Need For Speed Unbound");
        game.setEsrbRating("10+");
        game.setDescription("Exciting car racing with exotic cars");
        game.setPrice(new BigDecimal("59.95"));
        game.setStudio("Electronic Arts");
        game.setQuantity(499L);

        Game saveGame = new Game();
        saveGame.setId(3);
        saveGame.setTitle("Need For Speed Unbound");
        saveGame.setEsrbRating("10+");
        saveGame.setDescription("Exciting car racing with exotic cars");
        saveGame.setPrice(new BigDecimal("59.95"));
        saveGame.setStudio("Electronic Arts");
        saveGame.setQuantity(499L);

        Game saveGame2 = new Game();
        saveGame2.setId(4);
        saveGame2.setTitle("NBA 2K23");
        saveGame2.setEsrbRating("10+");
        saveGame2.setDescription("Michael Jordan Edition");
        saveGame2.setPrice(new BigDecimal("59.95"));
        saveGame2.setStudio("Take-Two Interactive");
        saveGame2.setQuantity(500L);

        doReturn(saveGame).when(clientGame).createGame(game);
        doReturn(saveGame).when(clientGame).getGameInfo(3);
        doReturn(saveGame2).when(clientGame).getGameInfo(4);

        List<Game> gameList = new ArrayList<>();
        gameList.add(saveGame);
        doReturn(gameList).when(clientGame).getGamesByTitle("Need For Speed Unbound");
        doReturn(gameList).when(clientGame).getGamesByStudio("Electronic Arts");

        List<Game> gameList2 = new ArrayList<>();
        gameList2.add(saveGame);
        gameList2.add(saveGame2);
        doReturn(gameList2).when(clientGame).getGamesByEsrbRating("10+");
        doReturn(gameList2).when(clientGame).getAllGames();
    }

    // Feign Client Setup for TShirt
    private void setUpTShirtFeignClientMock() {
        clientTShirt = mock(GameStoreCatalogFeignClient.class);
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("Long Sleeve");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(4);

        TShirt saveTShirt = new TShirt();
        saveTShirt.setId(54);
        saveTShirt.setSize("Large");
        saveTShirt.setColor("Blue");
        saveTShirt.setDescription("Long Sleeve");
        saveTShirt.setPrice(new BigDecimal("19.99"));
        saveTShirt.setQuantity(4);

        TShirt saveTShirt2 = new TShirt();
        saveTShirt2.setId(55);
        saveTShirt2.setSize("Medium");
        saveTShirt2.setColor("Red");
        saveTShirt2.setDescription("V-Neck");
        saveTShirt2.setPrice(new BigDecimal("19.99"));
        saveTShirt2.setQuantity(150);

        doReturn(saveTShirt).when(clientTShirt).createTShirt(tShirt);
        doReturn(saveTShirt).when(clientTShirt).getTShirt(54);
        doReturn(saveTShirt2).when(clientTShirt).getTShirt(55);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(saveTShirt);
        doReturn(tShirtList).when(clientTShirt).getTShirtsBySize("Large");
        doReturn(tShirtList).when(clientTShirt).getTShirtsByColor("Blue");

        List<TShirt> tShirtAll = new ArrayList<>();
        tShirtAll.add(saveTShirt);
        tShirtAll.add(saveTShirt2);
        doReturn(tShirtAll).when(clientTShirt).getAllTShirts();
    }
}