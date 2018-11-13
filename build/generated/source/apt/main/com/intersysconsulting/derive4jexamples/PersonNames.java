package com.intersysconsulting.derive4jexamples;

import fj.Equal;
import fj.Hash;
import fj.Ord;
import fj.Ordering;
import fj.Show;
import fj.data.Option;
import fj.data.Stream;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;

public final class PersonNames {
  @SuppressWarnings("rawtypes")
  private static Show personNameShow;

  @SuppressWarnings("rawtypes")
  private static Ord personNameOrd;

  @SuppressWarnings("rawtypes")
  private static Equal personNameEqual;

  @SuppressWarnings("rawtypes")
  private static Hash personNameHash;

  private PersonNames() {
  }

  /**
   *  This method is reexported with public modifier as
   *  {@link PersonNames#parseName(String)}. Also the javadoc is copied over.
   *
   *  @param value unparse string
   *  @return a valid {@link PersonName}, maybe.
   */
  public static Option<PersonName> parseName(@NonNull String value) {
    return com.intersysconsulting.derive4jexamples.PersonName.parseName(value);
  }

  static PersonName Name0(String value) {
    return new Name(value);
  }

  public static PersonName lazy(Supplier<PersonName> personName) {
    return new Lazy(personName);
  }

  public static CasesMatchers.TotalMatcher_Name cases() {
    return CasesMatchers.totalMatcher_Name;
  }

  public static String getValue(PersonName personName) {
    return personName.match((value) -> value);
  }

  static Function<PersonName, PersonName> setValue0(String newValue) {
    return modValue0(__ -> newValue);
  }

  static Function<PersonName, PersonName> modValue0(Function<String, String> valueMod) {
    return personName -> personName.match((value) -> Name0(valueMod.apply(value)));
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Show<PersonName> personNameShow() {
    Show<PersonName> _personNameShow = personNameShow;
    if (_personNameShow == null) {
      personNameShow = _personNameShow = Show.show(personName -> personName.match(
        (value) -> Stream.fromString("Name(").append(() -> Show.stringShow.show(value)).append(Stream.fromString(")"))
      ));
    }
    return _personNameShow;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Ord<PersonName> personNameOrd() {
    Ord<PersonName> _personNameOrd = personNameOrd;
    if (_personNameOrd == null) {
      personNameOrd = _personNameOrd = Ord.ordDef(personName1 -> personName1.match(
        (value1) -> personName2 -> personName2.match(
          (value2) -> {
            Ordering o = Ordering.EQ;
            o = Ord.stringOrd.compare(value1, value2);
            if (o != Ordering.EQ) return o;
            return o;
          }
        )
      ));
    }
    return _personNameOrd;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Equal<PersonName> personNameEqual() {
    Equal<PersonName> _personNameEqual = personNameEqual;
    if (_personNameEqual == null) {
      personNameEqual = _personNameEqual = Equal.equalDef(personName1 -> personName1.match(
        (value1) -> personName2 -> personName2.match(
          (value2) -> Equal.stringEqual.eq(value1, value2)
        )
      ));
    }
    return _personNameEqual;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Hash<PersonName> personNameHash() {
    Hash<PersonName> _personNameHash = personNameHash;
    if (_personNameHash == null) {
      personNameHash = _personNameHash = Hash.hash(personName -> personName.match(
        (value) -> 23 + Hash.stringHash.hash(value)
      ));
    }
    return _personNameHash;
  }

  private static final class Name extends PersonName {
    private final String value;

    Name(String value) {
      this.value = value;
    }

    @Override
    public <R> R match(Function<String, R> Name) {
      return Name.apply(this.value);
    }
  }

  private static final class Lazy extends PersonName {
    private volatile Supplier<PersonName> expression;

    private PersonName evaluation;

    Lazy(Supplier<PersonName> personName) {
      this.expression = personName;
    }

    private synchronized PersonName _evaluate() {
      Lazy lazy = this;
      while (true) {
        Supplier<PersonName> expr = lazy.expression;
        if (expr == null) {
          evaluation = lazy.evaluation;
          break;
        }
        else {
          PersonName eval = expr.get();
          if (eval instanceof Lazy) {
            lazy = (Lazy) eval;
          }
          else {
            evaluation = eval;
            break;
          }
        }
      }
      expression = null;
      return evaluation;
    }

    @Override
    public <R> R match(Function<String, R> Name) {
      return (this.expression == null ? this.evaluation : _evaluate()).match(Name);
    }
  }

  public static class CasesMatchers {
    private static final TotalMatcher_Name totalMatcher_Name = new TotalMatcher_Name();

    private CasesMatchers() {
    }

    public static final class TotalMatcher_Name {
      TotalMatcher_Name() {
      }

      public final <R> Function<PersonName, R> Name(Function<String, R> Name) {
        return personName -> personName.match(Name);
      }

      public final <R> Function<PersonName, R> Name_(R r) {
        return this.Name((value) -> r);
      }
    }
  }
}
