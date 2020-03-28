package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.model.KudosPeriod;
import com.asajenko.kudos.model.Period;
import com.asajenko.kudos.repository.KudosPeriodRepository;
import com.asajenko.kudos.repository.KudosRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KudosServiceTest {

    @Mock
    KudosRepository kudosRepositoryMock;

    @Test
    void randomKudos_success() {
        List<Kudos> kudosList = Lists.list(new Kudos(), new Kudos(), new Kudos(), new Kudos());

        KudosService kudosService = new KudosService(null, null);

        assertEquals(4, kudosList.size());
        List<Kudos> firstLottery = kudosService.randomKudos(1, kudosList);
        assertEquals(3, kudosList.size());
        List<Kudos> secondLottery = kudosService.randomKudos(2, kudosList);
        assertEquals(1, kudosList.size());
        assertFalse(secondLottery.containsAll(firstLottery));
        assertFalse(kudosList.containsAll(firstLottery));
        assertFalse(kudosList.containsAll(secondLottery));
        assertEquals(1, firstLottery.size());
        assertEquals(2, secondLottery.size());
    }

    @Test
    void randomKudos_secondLotteryCountGreaterThanAllKudosList_success() {
        List<Kudos> kudosList = Lists.list(new Kudos(), new Kudos(), new Kudos(), new Kudos());

        KudosService kudosService = new KudosService(null, null);

        assertEquals(4, kudosList.size());
        List<Kudos> firstLottery = kudosService.randomKudos(1, kudosList);
        assertEquals(3, kudosList.size());
        List<Kudos> secondLottery = kudosService.randomKudos(8, kudosList);
        assertEquals(0, kudosList.size());
        assertEquals(1, firstLottery.size());
        assertEquals(3, secondLottery.size());
    }

    @Test
    void randomKudos_kudosListIsNull_success() {
        List<Kudos> kudosList = null;

        KudosService kudosService = new KudosService(null, null);

        List<Kudos> firstLottery = kudosService.randomKudos(1, kudosList);
        assertNull(firstLottery);
    }

    @Test
    void randomKudos_kudosListIsEmpty_success() {
        List<Kudos> kudosList = Lists.emptyList();

        KudosService kudosService = new KudosService(null, null);

        List<Kudos> firstLottery = kudosService.randomKudos(1, kudosList);
        assertNull(firstLottery);
    }

    @Test
    void saveKudosPeriod_oneKudos_success() {
        Period period = new Period();
        Kudos kudos = new Kudos();
        List<Kudos> kudosList = Collections.singletonList(kudos);

        KudosPeriodRepository mock = mock(KudosPeriodRepository.class);
        KudosService kudosService = new KudosService(kudosRepositoryMock, mock);
        kudosService.saveKudosPeriod(period, kudosList, KudosPeriodType.READ);

        ArgumentCaptor<KudosPeriod> argumentCaptor = ArgumentCaptor.forClass(KudosPeriod.class);
        verify(mock, times(1)).save(argumentCaptor.capture());
        List<KudosPeriod> values = argumentCaptor.getAllValues();
        assertAll(() -> {
            assertEquals(1, values.size());
            assertEquals(period.hashCode(), values.get(0).getPeriod().hashCode());
            assertEquals(kudos.hashCode(), values.get(0).getKudos().hashCode());
            assertEquals(KudosPeriodType.READ, values.get(0).getType());
        });
    }

    @Test
    void saveKudosPeriod_oneKudos_win_success() {
        Period period = new Period();
        Kudos kudos = new Kudos();
        List<Kudos> kudosList = Collections.singletonList(kudos);

        KudosPeriodRepository mock = mock(KudosPeriodRepository.class);
        KudosService kudosService = new KudosService(kudosRepositoryMock, mock);
        kudosService.saveKudosPeriod(period, kudosList, KudosPeriodType.WIN);

        ArgumentCaptor<KudosPeriod> argumentCaptor = ArgumentCaptor.forClass(KudosPeriod.class);
        verify(mock, times(1)).save(argumentCaptor.capture());
        List<KudosPeriod> values = argumentCaptor.getAllValues();
        assertAll(() -> {
            assertEquals(1, values.size());
            assertEquals(period.hashCode(), values.get(0).getPeriod().hashCode());
            assertEquals(kudos.hashCode(), values.get(0).getKudos().hashCode());
            assertEquals(KudosPeriodType.WIN, values.get(0).getType());
        });
    }

    @Test
    void saveKudosPeriod_threeKudos_success() {
        Period period = new Period();
        Kudos kudos = new Kudos();
        Kudos kudos1 = new Kudos();
        Kudos kudos2 = new Kudos();
        List<Kudos> kudosList = List.of(kudos, kudos1, kudos2);

        KudosPeriodRepository mock = mock(KudosPeriodRepository.class);
        KudosService kudosService = new KudosService(kudosRepositoryMock, mock);
        kudosService.saveKudosPeriod(period, kudosList, KudosPeriodType.READ);

        ArgumentCaptor<KudosPeriod> argumentCaptor = ArgumentCaptor.forClass(KudosPeriod.class);
        verify(mock, times(3)).save(argumentCaptor.capture());
        List<KudosPeriod> values = argumentCaptor.getAllValues();
        assertAll(() -> {
            assertEquals(3, values.size());
            assertEquals(period.hashCode(), values.get(0).getPeriod().hashCode());
            assertEquals(kudos.hashCode(), values.get(0).getKudos().hashCode());
            assertEquals(KudosPeriodType.READ, values.get(0).getType());
            assertEquals(period.hashCode(), values.get(1).getPeriod().hashCode());
            assertEquals(kudos1.hashCode(), values.get(1).getKudos().hashCode());
            assertEquals(KudosPeriodType.READ, values.get(1).getType());
            assertEquals(period.hashCode(), values.get(2).getPeriod().hashCode());
            assertEquals(kudos2.hashCode(), values.get(2).getKudos().hashCode());
            assertEquals(KudosPeriodType.READ, values.get(2).getType());
        });
    }

    @Test
    void saveKudosPeriod_nullKudosList_success() {
        Period period = new Period();

        KudosPeriodRepository kudosPeriodRepositoryMock = mock(KudosPeriodRepository.class);
        KudosService kudosService = new KudosService(kudosRepositoryMock, kudosPeriodRepositoryMock);
        kudosService.saveKudosPeriod(period, null, KudosPeriodType.READ);

        verifyNoInteractions(kudosPeriodRepositoryMock);
    }

    @Test
    void saveKudosPeriod_emptyKudosList_success() {
        Period period = new Period();

        KudosPeriodRepository kudosPeriodRepositoryMock = mock(KudosPeriodRepository.class);
        KudosService kudosService = new KudosService(kudosRepositoryMock, kudosPeriodRepositoryMock);
        kudosService.saveKudosPeriod(period, Lists.emptyList(), KudosPeriodType.READ);

        verifyNoInteractions(kudosPeriodRepositoryMock);
    }
}